package com.atguigu;

import com.atguigu.entity.Result;

import java.util.Map;

public interface OrderService {
    Result save(Map<String, String> map) throws Exception;

    Map<String, Object> findById(Integer id);
}
