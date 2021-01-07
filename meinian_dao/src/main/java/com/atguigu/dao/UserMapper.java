package com.atguigu.dao;

import com.atguigu.pojo.User;

public interface UserMapper {
    User getUserInforByUsername(String username);
}
