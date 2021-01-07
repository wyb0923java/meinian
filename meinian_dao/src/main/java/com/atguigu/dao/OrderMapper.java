package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int selectOrderByCondition(@Param("memberId") Integer memberId, @Param("orderDate") String orderDate, @Param("setmealId") String setmealId);

    Map<String,Object> findById(Integer id);

    int getTodayOrderNumber(String date);

    int getTodayVisitsNumber(String date);

    int getThisWeekAndMonthOrderNumber(Map<String, Object> map);

    int getThisWeekAndMonthVisitsNumber(Map<String, Object> map);

    List<Map<String,Object>> findHotSetmeal();
}