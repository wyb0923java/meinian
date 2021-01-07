package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.PermissionService;
import com.atguigu.dao.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> getAuthoritiesByUid(Integer id) {
        return permissionMapper.getAuthoritiesByUid(id);
    }
}
