package com.atguigu;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelgroupService {
    void add(TravelGroup travelGroup, Integer[] tids);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    TravelGroup findById(Integer id);

    List<TravelGroup> findTravelItemIdByTravelgroupId(Integer id);

    void edit(Integer[] travelItemIds,TravelGroup travelGroup);

    //setmeal（添加弹窗中的travelGroup的信息）
    List<TravelGroup> findAll();
}
