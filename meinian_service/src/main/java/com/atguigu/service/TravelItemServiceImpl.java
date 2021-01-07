package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.TravelItemService;
import com.atguigu.dao.TravelItemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelItemService.class)
@Transactional
public class TravelItemServiceImpl implements TravelItemService  {

    @Autowired
    private TravelItemMapper travelItemMapper;

    @Override
    public void add(TravelItem travelItem) {
        travelItemMapper.add(travelItem);
    }

    @Override
    public PageResult getPageInfo(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<TravelItem> list  =  travelItemMapper.getTravelItemList(queryPageBean);

        //返回总记录数
        PageInfo<TravelItem> page = new PageInfo<TravelItem>(list);
        return new PageResult(page.getTotal(),page.getList());
    }


    /*
    在实际开发中给，如果要删除的数据有关联数据，一般有如下几种方法
        第一种：如果删除的数据有关联数据，抛出异常，不让删除
        第二种：如果删除的数据有关联数据，将该数据与关联数据一起删除（代码的方式）

        第三种：如果删除的数据有关联数据，将该数据与关联数据一起删除（数据库设置的方式）
            在关联表中设计表 --外键--删除时改为CASCADE
     */
    @Override
    public void deleteById(Integer id) {
        travelItemMapper.deleteById(id);
    }

    @Override
    public TravelItem findById(Integer id) {
        return travelItemMapper.findById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemMapper.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemMapper.findAll();
    }


}
