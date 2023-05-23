package LoadBalancer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Converter {

    private static Logger log = LoggerFactory.getLogger(Converter.class);
    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancer;
    private CircuitBreaker circuitBreaker;



    public Converter(ConfigurableApplicationContext context) {
        this.restTemplate = context.getBean(RestTemplateBuilder.class).build();
        this.loadBalancer = context.getBean(LoadBalancerClient.class);
        this.circuitBreaker = context.getBean(CircuitBreakerFactory.class).create("converter");
    }


    public void throwRequest(String fromCurrency, String toCurrency, int value) {
        log.info("request: " + fromCurrency + " -> " + toCurrency + " value = " + value);
        String serviceURL = loadBalancer.choose("Producer").getUri().toString();
        String response = circuitBreaker.run(
                () -> this.restTemplate.getForObject(serviceURL + "/convert/from/" + fromCurrency + "/to/" + toCurrency + "?value=" + value,String.class));
        log.info("response: " + response);
    }
}
