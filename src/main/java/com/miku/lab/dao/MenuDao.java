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
    List<SysMenu> searchMenu(Map<String,Object>map);

    //获取单纯单菜单的信息
    List<SysMenu> getMenu();

    //添加菜单信息
    int addMenu(Map<String,Object>map);

    //通过菜单id查询菜单
    SysMenu getMenuByMenuId(String menu_id);

    //通过href查询菜单
    SysMenu getMenuByHref(String href);

    //通过title查询菜单
    SysMenu getMenuByTitle(String title);

    //通过perms查询菜单
    SysMenu getMenuByPerms(String perms);
}
