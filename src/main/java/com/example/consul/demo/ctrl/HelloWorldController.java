package com.example.consul.demo.ctrl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


/**
 * @Description TODO
 * @Author zouxiaodong
 * @Date 2020/07/31 16:37
 */
@RestController
@RequestMapping("/ctrl")
public class HelloWorldController {

    @RequestMapping("/test")
    public String test(@RequestParam String name){
        return String.format("Hello world from %s!",name);
    }

}
