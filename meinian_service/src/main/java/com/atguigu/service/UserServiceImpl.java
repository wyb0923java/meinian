package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.UserService;
import com.atguigu.dao.UserMapper;
import com.atguigu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInforByUsername(String username) {
        return userMapper.getUserInforByUsername(username);
    }
}
