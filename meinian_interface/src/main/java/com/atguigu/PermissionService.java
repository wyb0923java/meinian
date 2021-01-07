package com.atguigu;

import java.util.List;

public interface PermissionService {
    List<String> getAuthoritiesByUid(Integer id);
}
