package com.example.consul.consumer.demo.ctrl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "tool-service")
public interface ToolServiceClient {

    @GetMapping("/tools/1")
    Object getTools();
}
