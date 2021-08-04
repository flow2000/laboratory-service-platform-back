package com.miku.lab.service.imp;/*
 *@author 邓涛
 *@data 2021/8/4 20:17
 *@version:1.1
 */

import com.miku.lab.dao.MenuDao;
import com.miku.lab.entity.Article;
import com.miku.lab.entity.SysMenu;
import com.miku.lab.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;


    @Override
    public List<SysMenu> getAllMenu(){
        List<SysMenu> sysMenus = menuDao.getAllMenu();
        if (sysMenus!=null){
            return sysMenus;
        }else{
            return null;
        }
    }
}
