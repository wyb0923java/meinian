package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.TravelgroupService;
import com.atguigu.dao.TravelgroupMapper;
import com.atguigu.dao.TravelgroupTravelitemMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = TravelgroupService.class)
@Transactional
public class TravelgroupServiceImpl implements TravelgroupService {

    @Autowired
    private TravelgroupMapper travelgroupMapper;

    @Autowired
    private TravelgroupTravelitemMapper travelgroupTravelitemMapper;

    @Override
    public void add(TravelGroup travelGroup, Integer[] tids) {
        //1、先添加跟团游的信息，并返回跟团游的ID
        travelgroupMapper.save(travelGroup);
        //跟团游的ID
        //travelGroup.getId();
        //2、跟团游的id和tids保存到中间表中
        travelgroupTravelitemMapper.insert(travelGroup.getId(),tids);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        //分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<TravelGroup> list = travelgroupMapper.selectList(queryPageBean);

        //封装到列表
        PageInfo<TravelGroup> page = new PageInfo<TravelGroup>(list);
        return new PageResult(page.getTotal(),page.getList());
    }

    @Override
    public void deleteById(Integer id) {
        travelgroupMapper.deleteById(id);
    }

    @Override
    public TravelGroup findById(Integer id) {
        return travelgroupMapper.findById(id);
    }

    @Override
    public List<TravelGroup> findTravelItemIdByTravelgroupId(Integer id) {
        return travelgroupTravelitemMapper.findTravelItemIdByTravelgroupId(id);
    }

    @Override
    public void edit(Integer[] travelItemIds,TravelGroup travelGroup) {
        //1、修改travelGroup
        travelgroupMapper.edit(travelGroup);

        //2、修改中间表
        //删除中间表数据
        travelgroupTravelitemMapper.deleteByTravelGroupId(travelGroup.getId());
        //添加中间表的id
        travelgroupTravelitemMapper.insert(travelGroup.getId(),travelItemIds);
    }


    //setmeal（添加弹窗中的travelGroup的信息）
    @Override
    public List<TravelGroup> findAll() {
        return travelgroupMapper.findAll();
    }
}
