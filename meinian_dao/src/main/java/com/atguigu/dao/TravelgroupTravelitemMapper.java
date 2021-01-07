package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelgroupTravelitemMapper {
    void insert(@Param("id") Integer id, @Param("tids") Integer[] tids);

    List<TravelGroup> findTravelItemIdByTravelgroupId(Integer id);

    void deleteByTravelGroupId(Integer id);
}
