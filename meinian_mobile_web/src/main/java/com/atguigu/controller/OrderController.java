package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.OrderService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String,String> map){
        String requestValidateCode = map.get("validateCode");
        String telephone = map.get("telephone");
        String redisValidateCode = jedisPool.getResource().get(telephone);

        //判断验证码  是否空
        if (redisValidateCode != null && !redisValidateCode.trim().equals("")) {

            //判断验证码  是否一致
            if (redisValidateCode.equals(requestValidateCode)) {


                try {
                    //保存到数据表中
                    Result result = orderService.save(map);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(false,e.getMessage());
                }


            } else {
                return new Result(false, MessageConstant.VALIDATECODE_ERROR);
            }
        }else{
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

    }

    //提交预约成功后的信息findById
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map<String,Object> map= orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
