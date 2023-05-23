package LoadBalancer;

import LoadBalancer.services.Converter;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;




@SpringBootApplication
@EnableDiscoveryClient
public class LoadBalancer {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(LoadBalancer.class)
				.web(WebApplicationType.NONE)
				.run(args);

		Converter converter = new Converter(context);

		while (true) {
			converter.throwRequest("RUB","USD",80);
			Thread.sleep(1000);
		}
	}

}
