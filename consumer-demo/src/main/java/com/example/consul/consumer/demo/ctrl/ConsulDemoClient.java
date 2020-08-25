package com.example.consul.consumer.demo.ctrl;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-demo")
public interface ConsulDemoClient {

    @RequestMapping("/ctrl/test")
    String test(@RequestParam String name);
}
