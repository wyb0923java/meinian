package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.OrderSettingService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result uploadExcel(MultipartFile excelFile) {

        try {
            //解析excelFile   返回除了第一行之外的其他行
            List<String[]> strings = POIUtils.readExcel(excelFile);

            List<OrderSetting> list = new ArrayList<OrderSetting>();

            //遍历strings，转换为Data类型
            for (String[] string : strings) {
                //将索引为0的字符串类型转换为日期类型
//                new Date(string[0]);
                //将索引为的字符串类型转换为数值类型
//                Integer.parseInt(string[1]);
                //封装到ordSetting对象中(封装了一行数据)
                OrderSetting orderSetting = new OrderSetting(new Date(string[0]), Integer.parseInt(string[1]));
                list.add(orderSetting);

            }
            //for循环遍历完之后，excel表格中的多行遍历完了，添加到list集合中了
            //将list插入到数据表中，调用service
            orderSettingService.batchInert(list);
            return new Result(true, MessageConstant.EXCEL_UPLOAD_SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EXCEL_UPLOAD_FAIL);
        }
    }

//    查询日历信息
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map<String,Integer>> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //预约设置
    @RequestMapping("editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }

    }
}
