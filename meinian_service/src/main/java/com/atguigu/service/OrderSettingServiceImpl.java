package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.OrderSettingService;
import com.atguigu.dao.OrderSettingMapper;
import com.atguigu.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void batchInert(List<OrderSetting> list) {
        //遍历OrderSetting
        for(OrderSetting orderSetting : list){
            //查询这个时间里有没有数据
            Date orderdate = orderSetting.getOrderdate();
            //根据orderdate查数据
            Long count = orderSettingMapper.countByDate(orderdate);
            if (count>0){
                orderSettingMapper.updateNumberByDate(orderSetting);
            }else{
                orderSettingMapper.insert(orderSetting);
            }
        }
    }

    @Override
    public List<Map<String,Integer>> getOrderSettingByMonth(String date) {
        StringBuilder sb = new StringBuilder();
        sb.append(date).append("-%");
        List<OrderSetting> orderSettings = orderSettingMapper.getOrderSettingByMonth(sb.toString());

        List<Map<String,Integer>> maps = new ArrayList<Map<String, Integer>>();
        for (OrderSetting orderSetting:orderSettings){
            int date1 = orderSetting.getOrderdate().getDate();
            Integer number = orderSetting.getNumber();
            Integer reservations = orderSetting.getReservations();
            HashMap<String, Integer> map = new HashMap<>();
            map.put("date",date1);
            map.put("number",number);
            map.put("reservations",reservations);

            maps.add(map);
        }
        return maps;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //查数据
        Long aLong = orderSettingMapper.countByDate(orderSetting.getOrderdate());
        if (aLong>0){
            //大于0，有数据执行editNumberByDate，进行修改
            orderSettingMapper.editNumberByDate(orderSetting);
        }else{
            //小于0，说明没有数据，进行添加
            orderSettingMapper.insert(orderSetting);
        }

    }
}
