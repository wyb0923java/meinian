package com.atguigu.dao;

import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;

import java.util.List;

/*************************************************
                时间: 2020-12-25
                作者: 刘  辉
                描述: 
  ************************************************/
public interface TravelItemMapper {
    void add(TravelItem travelitem);

    List<TravelItem> getTravelItemList(QueryPageBean queryString);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    void edit(TravelItem travelItem);

    List<TravelItem> findAll();
}
