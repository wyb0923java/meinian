package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.SetmealService;
import com.atguigu.dao.SetmealMapper;
import com.atguigu.dao.SetmealTravelgroupMapper;
import com.atguigu.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealTravelgroupMapper setmealTravelgroupMapper;

    //添加
    @Override
    public void add(Setmeal setmeal, Integer[] tids) {
        //1、先添加setmeal套餐信息,并返回id
        setmealMapper.save(setmeal);
        //2、在添加travelgroup跟团游信息
        setmealTravelgroupMapper.insert(setmeal.getId(),tids);
    }

    @Override
    public List<Setmeal> getSetmeal() {
        return setmealMapper.getSetmeal();
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealMapper.findSetmealCount();
    }
}
