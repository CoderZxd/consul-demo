package com.example.consul.demo.ctrl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description TODO
 * @Author zouxiaodong
 * @Date 2020/07/31 16:37
 */
@RestController
@RequestMapping("/ctrl")
public class HelloWorldController {

    @RequestMapping("/test")
    public String test(){
        return "Hello world from consul demo！";
    }

}
