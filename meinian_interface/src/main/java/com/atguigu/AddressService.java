package com.atguigu;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();

    PageResult getPageInfo(QueryPageBean queryPageBean);
}
