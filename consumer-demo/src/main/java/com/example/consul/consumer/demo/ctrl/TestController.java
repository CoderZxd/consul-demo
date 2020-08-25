package com.example.consul.consumer.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Value("${consul.demo:Hello world from consul demo}")
    private String consulDemo;

    public String getConsulDemo() {
        return consulDemo;
    }

    public void setConsulDemo(String consulDemo) {
        this.consulDemo = consulDemo;
    }

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


    @Autowired
    private ToolServiceClient toolServiceClient;

    @Autowired
    private ConsulDemoClient consulDemoClient;

    @RequestMapping("/getTest")
    public String getTest(@RequestParam String name){
        Map<String,String> map = new HashMap(4);
        map.put("name",name);
        return this.restTemplate.getForObject("http://consul-demo/ctrl/test?name={1}",String.class,name)+"testConfig===>"+testConfig+"consumerDemo:"+consumerDemo+"consulDemo:"+consulDemo;
//        return this.restTemplate.getForObject("http://consul-demo/ctrl/test",String.class,map)+"testConfig===>"+testConfig+"consumerDemo:"+consumerDemo;
    }

    @RequestMapping("/getTest1")
    public String getTest1(){
        return loadBalancer.choose("consul-demo").getUri().toString();
    }

    @RequestMapping("/getTest2")
    public Object getTest2(){
        List<ServiceInstance> instances = discoveryClient.getInstances("tool-service");
        if (instances != null && instances.size() > 0 ) {
            for(ServiceInstance serviceInstance:instances){
                System.out.println("Uri:"+serviceInstance.getUri()+",instanceId:"+serviceInstance.getInstanceId());
            }
        }
        return discoveryClient.getInstances("tool-service");
    }

    @RequestMapping("/getTest3")
    public Object getTest3(){
        return this.restTemplate.getForObject("http://tool-service/tools/1",Object.class);
    }

    @RequestMapping("/getTest4")
    public Object getTest4(){
        return toolServiceClient.getTools();
    }

    @RequestMapping("/getTest5")
    public String getTest5(@RequestParam String name){
        return consulDemoClient.test(name);
    }
}
