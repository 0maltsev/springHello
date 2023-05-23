package com.mipt.omaltsev.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Producer {
    public static void main(String[] args) {
        System.out.println("Producer starts");
        SpringApplication.run(Producer.class, args);
    }
}