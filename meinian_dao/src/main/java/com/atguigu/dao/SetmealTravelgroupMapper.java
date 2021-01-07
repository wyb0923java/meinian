package com.atguigu.dao;

import org.apache.ibatis.annotations.Param;

public interface SetmealTravelgroupMapper {
    void insert(@Param("id") Integer id, @Param("tids") Integer[] tids);
}
