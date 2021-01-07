package com.atguigu.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.TravelgroupService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travelgroup")
public class TravelgroupController {

    @Reference
    private TravelgroupService travelgroupService;

//    增
    @RequestMapping("/add")
    public Result add(@RequestBody TravelGroup travelGroup, Integer[] tids){
        try {
            travelgroupService.add(travelGroup,tids);
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.ADD_TRAVELGROUP_FAIL);
        }
    }

//    查
    @RequestMapping("/findPage")
        public Result findPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = travelgroupService.findPage(queryPageBean);
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

//    删
    @RequestMapping("/delete")
    public Result delete(Integer id){

        try {
            travelgroupService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELGROUP_FAIL);
        }
    }

//    改（获取 跟团游数据）
    @RequestMapping("/findById")
    public Result findById(Integer id){

        try {
            TravelGroup travelGroup = travelgroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

//    改（获取中间表信息）
    @RequestMapping("/findTravelItemIdByTravelgroupId")
    public Result findTravelItemIdByTravelgroupId(Integer id){

        try {
            List<TravelGroup> list = travelgroupService.findTravelItemIdByTravelgroupId(id);
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

//    改（数据）
    @RequestMapping("/edit")
    public Result edit( Integer[] travelItemIds , @RequestBody TravelGroup travelGroup){

        try {
            travelgroupService.edit(travelItemIds,travelGroup);
            return new Result(true, MessageConstant.EDIT_TRAVELGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELGROUP_FAIL);
        }
    }

//    setmeal（添加弹窗中的travelGroup的信息）
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelGroup> list = travelgroupService.findAll();
            return new Result(true, MessageConstant.QUERY_TRAVELGROUP_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }

}
