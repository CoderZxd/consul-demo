package com.example.consul.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulDemoApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate loadbalanceRestTemplate(){
        return new RestTemplate();
    }
}
