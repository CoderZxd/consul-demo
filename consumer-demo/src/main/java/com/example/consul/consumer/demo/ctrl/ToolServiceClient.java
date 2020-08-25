package com.example.consul.consumer.demo.ctrl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "tool-service",name = "tool-service",url = "http://192.168.61.53:8009")
public interface ToolServiceClient {

    @GetMapping("/tools/1")
    Object getTools();
}
