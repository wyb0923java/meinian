package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.AddressService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Address;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;


    @RequestMapping("/findPage")
    public Result getPageInfo(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult result = addressService.getPageInfo(queryPageBean);
            return new Result(true, MessageConstant.QUERY_ADDRESS_SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ADDRESS_FAIL);
        }
    }


    @RequestMapping("/findAllMaps")
    public Map findAll(){
        Map map=new HashMap();
        List<Address> addressList = addressService.findAll();

        //1、定义分店坐标集合
        List<Map> gridnMaps=new ArrayList<>();
        //2、定义分店名称集合
        List<Map> nameMaps=new ArrayList();
        for (Address address : addressList) {
            Map gridnMap=new HashMap();
            // 获取经度
            gridnMap.put("lng",address.getLng());
            // 获取纬度
            gridnMap.put("lat",address.getLat());
            gridnMaps.add(gridnMap);

            Map nameMap=new HashMap();
            // 获取地址的名字
            nameMap.put("addressName",address.getAddressname());
            nameMaps.add(nameMap);
        }
        // 存放经纬度
        map.put("gridnMaps",gridnMaps);
        // 存放名字
        map.put("nameMaps",nameMaps);
        return map;
    }
}
