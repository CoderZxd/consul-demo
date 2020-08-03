package com.example.consul.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author zouxiaodong
 * @Date 2020/07/31 16:37
 */
@RestController
@RequestMapping("/ctrl")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public String test(){
        return "Hello world";
    }

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getTest")
    public String getTest(){
        return this.restTemplate.getForObject("http://consul-demo/ctrl/test",String.class);
    }

    @RequestMapping("/getTest1")
    public String getTest1(){
        return loadBalancer.choose("consul-demo").getUri().toString();
    }

    @RequestMapping("/getTest2")
    public Object getTest2(){
        return discoveryClient.getInstances("consul-demo");
    }
}
