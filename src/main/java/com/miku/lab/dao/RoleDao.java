package com.miku.lab.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleDao {
    List<Map> getAllRole();

    int getTotalNumber();

    List<Map> getPageRole(int p, int m);
}
