package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 13:46
 *@version:1.1
 */

import com.miku.lab.entity.LabInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabDao {
    List<LabInfo> getLabList();
}
