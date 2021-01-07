package com.atguigu;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    void batchInert(List<OrderSetting> list);


    List<Map<String, Integer>> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
