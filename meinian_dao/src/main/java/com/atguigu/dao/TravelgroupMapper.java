package com.atguigu.dao;

import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelgroupMapper {
    void save(TravelGroup travelGroup);

    List<TravelGroup> selectList(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    TravelGroup findById(Integer id);

    void edit(TravelGroup travelGroup);

    List<TravelGroup> findAll();
}
