package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealMapper {

    void save(Setmeal setmeal);

    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
