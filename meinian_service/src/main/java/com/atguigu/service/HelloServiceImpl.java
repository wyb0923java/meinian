package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.HelloService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = HelloService.class)
@Transactional
public class HelloServiceImpl implements HelloService {


    @Override
    public String SayHello() {
        return "heihei张三";
    }
}
