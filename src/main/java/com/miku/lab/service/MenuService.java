package com.miku.lab.service;/*
 *@author 邓涛
 *@data 2021/8/4 20:17
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MenuService {

    //获取所有的菜单接口
    public List<SysMenu> getAllMenu();

    //查询菜单
    public List<SysMenu> searchMenu(String searchKey,String searchValue);

    //获取单纯菜单信息
    public List<SysMenu> getMenu();

    //添加菜单
    public String addMenu(Map<String, Object> map);

    //删除菜单
    public int delMenu(String menuId);

    //获取菜单详细接口
    Object getMenuDetail(String menuId);

    //更新菜单信息
    public int updateMenu(Map<String, Object>map);
}
