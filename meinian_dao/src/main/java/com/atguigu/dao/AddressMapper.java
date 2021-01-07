package com.atguigu.dao;

import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;

public interface AddressMapper {
    List<Address> findAll();

    List<Address> getPageInfo(QueryPageBean queryPageBean);
}
