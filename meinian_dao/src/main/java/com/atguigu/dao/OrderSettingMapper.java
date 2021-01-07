package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderSettingMapper {

    List<OrderSetting> getOrderSettingByMonth(String toString);

    Long countByDate(Date orderdate);

    void updateNumberByDate(OrderSetting orderSetting);

    void insert(OrderSetting orderSetting);

    void editNumberByDate(OrderSetting orderSetting);

    OrderSetting getOrderSettingByDate(String orderDate);

    void increaseReservations(String orderDate);

    void updateReservations(@Param("orderdate") String orderDate, @Param("reservations") int i);
}
