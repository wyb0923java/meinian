package com.atguigu.dao;

import java.util.List;

public interface PermissionMapper {
    List<String> getAuthoritiesByUid(Integer id);
}
