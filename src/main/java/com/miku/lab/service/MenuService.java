package com.miku.lab.service;/*
 *@author 邓涛
 *@data 2021/8/4 20:17
 *@version:1.1
 */

import com.miku.lab.entity.SysMenu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    //获取所有的菜单接口
    public List<SysMenu> getAllMenu();
}
