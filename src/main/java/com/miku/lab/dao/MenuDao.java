package com.miku.lab.dao;/*
 *@author 邓涛
 *@data 2021/8/4 20:17
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuDao {

    //获取所有的菜单信息
    List<SysMenu> getAllMenu();

    //分页获取菜单信息
    List<ArticleSort> getPageMenu(Map<String,Object> map);
}
