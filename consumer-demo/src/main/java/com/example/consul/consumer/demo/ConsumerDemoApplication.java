package com.example.consul.consumer.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }


    @LoadBalanced
    @Bean
    public RestTemplate loadbalanceRestTemplate(){
        return new RestTemplate();
    }

//    @Component
//    @RefreshScope
//    class SystemConfit implements ApplicationListener<RefreshEvent>{
//
//        @Override
//        public void onApplicationEvent(RefreshEvent event) {
//            System.out.println(event);
//        }
//    }
}
