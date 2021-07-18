package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 15:16
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.SystemOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SystemOperationDao {

    List<SystemOperation> getAllOperation();

    List<SystemOperation> getPageOper(Map<String,Object> map);

    List<SystemOperation> searchOper(Map<String,Object>map);            //获取查询数据

    List<SystemOperation> getSearchPageOper(Map<String,Object> map);    //获取查询所有分页数据

    int delOper(String id);

    int addOper(Map<String,Object>map);
}
