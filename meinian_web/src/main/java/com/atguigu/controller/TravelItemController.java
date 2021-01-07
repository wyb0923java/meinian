package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.TravelItemService;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController//@controller+@ResquestBody
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference
    private TravelItemService travelItemService;

    //增
    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem){

        try {
            travelItemService.add(travelItem);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);

        }
    }

    //查
    @RequestMapping("/findPage")
    public Result getPageInfo(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult result = travelItemService.getPageInfo(queryPageBean);
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }

    //删
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")
    public Result delete(Integer id){

        try {
            travelItemService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_TRAVELITEM_FAIL);

        }
    }

    //改（1、查数据）
    @RequestMapping("/findById")
    public Result findById(Integer id){

        try {
            TravelItem travelItem = travelItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);

        }
    }

    //改（2、改数据）
    @RequestMapping("/edit")
    public Result edit(@RequestBody TravelItem travelItem){

        try {
            travelItemService.edit(travelItem);
            return new Result(true, MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_TRAVELITEM_FAIL);

        }
    }

    //查询所有列表信息（跟团游中）
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelItem> list =travelItemService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }
}
