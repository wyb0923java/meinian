package com.atguigu.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.OrderService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.dao.OrderSettingMapper;
import com.atguigu.entity.Result;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.Map;


@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderMapper orderMapper;




        /************************************************************
         1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
         2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
         3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
         4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
         5、预约成功，更新当日的已预约人数
         ************************************************************/
        @Override
        public Result save(Map<String, String> map) throws Exception {
            Order order = new Order();
            String orderDate = map.get("orderDate");
            String telephone = map.get("telephone");
            Integer reservations=0;
            OrderSetting orderSetting = orderSettingMapper.getOrderSettingByDate(orderDate);
            // 1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
            if(orderSetting == null){
                return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
            }else {
                // 2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
                reservations = orderSetting.getReservations();
                Integer number = orderSetting.getNumber();
                if(reservations >= number){
                    return new Result(false, MessageConstant.ORDER_FULL);
                }
                //3.检查当前用户是否为会员
                Member member=memberMapper.selectMemberByTelephone(telephone);
                if(member == null){
                    member=new Member();
                    String name = map.get("name");
                    String sex = map.get("sex");
                    String idCard = map.get("idCard");
                    member.setName(name);
                    member.setSex(sex);
                    member.setIdcard(idCard);
                    member.setPhonenumber(telephone);
                    member.setRegtime(new Date());
                    memberMapper.insert(member);
                }
                //同一个用户在同一天预约了同一个套餐
                Integer memberId = member.getId();
                String setmealId= map.get("setmealId");
                int count= orderMapper.selectOrderByCondition(memberId,orderDate,setmealId);

                if(count >0){
                    return new Result(false,MessageConstant.HAS_ORDERED);
                }

                //可以预约：预约信息保存到t_order表，同时让一预约人数加1

                order.setMemberId(member.getId());
                order.setOrderdate( DateUtils.parseString2Date(orderDate));
                order.setOrdertype("微信预约");
                order.setOrderstatus("未出游");
                order.setSetmealId(Integer.parseInt(setmealId));
                orderMapper.insert(order);

                orderSettingMapper.updateReservations(orderDate,reservations+1);

            }

            return new Result(true,MessageConstant.ORDER_SUCCESS, order.getId());
        }

    @Override
    public Map<String, Object> findById(Integer id) {
        System.out.println("id = " + id);
        Map<String, Object> map=orderMapper.findById(id);
        System.out.println("map---service = " + map);
            return map;
    }

}
