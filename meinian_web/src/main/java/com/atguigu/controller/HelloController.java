package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
    
    @Reference
    private HelloService helloService;

    @RequestMapping(value = "/sayhello",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getHelloController(){
        return helloService.SayHello();
    }
}
