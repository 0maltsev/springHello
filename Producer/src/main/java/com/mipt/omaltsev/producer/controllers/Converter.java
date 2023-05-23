package com.mipt.omaltsev.producer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("convert")
public class Converter {

    private static final Logger log = LoggerFactory.getLogger(Converter.class);

    @Value("${instance.name}")
    private String instanceName;
    HashMap<String, Object> response = new HashMap<>();
    @GetMapping("from/{firstCurrency}/to/{secondCurrency}")
    public Map<String, Object> exchange(@PathVariable String firstCurrency, @PathVariable String secondCurrency,
                                        @RequestParam int value) {
        log.info(instanceName + " " + firstCurrency + " -> " + secondCurrency + " value = " + value);
        double result = (value * 0.01);
        response.put(secondCurrency, result);
        response.put("instanceId", instanceName);
        log.info(instanceName + " result: " + result);
        return response;
    }
}
