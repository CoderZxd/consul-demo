package com.example.consul.consumer.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-demo");
        if (instances != null && instances.size() > 0 ) {
            for(ServiceInstance serviceInstance:instances){
                System.out.println("Uri:"+serviceInstance.getUri()+",instanceId:"+serviceInstance.getInstanceId());
            }
        }
        return discoveryClient.getInstances("consul-demo");
    }
}
