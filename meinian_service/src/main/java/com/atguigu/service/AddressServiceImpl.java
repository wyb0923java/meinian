package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.AddressService;
import com.atguigu.dao.AddressMapper;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAll() {
        return addressMapper.findAll();
    }

    @Override
    public PageResult getPageInfo(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        List<Address> list=addressMapper.getPageInfo(queryPageBean);

        PageInfo<Address> page = new PageInfo<Address>(list);
        return new PageResult(page.getTotal(), page.getList());
    }
}
