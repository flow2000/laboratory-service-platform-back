package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/16 15:42
 *@version:1.1
 */

import com.miku.lab.entity.LinkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LinkInfoDao {

    List<LinkInfo> getLinkInfoList();
}
