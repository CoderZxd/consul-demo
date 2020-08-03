package com.example.consul.consumer.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
public class TestController {

    //读取配置中心
    //testConfig在consul中完整的目录是prefix + application name + data
    @Value("${config.test:Hello world}")
    private String testConfig;

    @Value("${consumer.demo:Hello world}")
    private String consumerDemo;

    public String getConsumerDemo() {
        return consumerDemo;
    }

    public void setConsumerDemo(String consumerDemo) {
        this.consumerDemo = consumerDemo;
    }

    public String getTestConfig() {
        return testConfig;
    }

    public void setTestConfig(String testConfig) {
        this.testConfig = testConfig;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getTest")
    public String getTest(){
        return this.restTemplate.getForObject("http://consul-demo/ctrl/test",String.class)+"testConfig===>"+testConfig+"consumerDemo:"+consumerDemo;
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
