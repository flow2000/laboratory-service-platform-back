package com.miku.lab.service.imp;/*
 *@author 邓涛
 *@data 2021/8/4 20:17
 *@version:1.1
 */

import com.miku.lab.dao.MenuDao;
import com.miku.lab.entity.Article;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.SysMenu;
import com.miku.lab.service.MenuService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuDao menuDao;


    /**
     * 获取菜单信息
     * @return
     */
    @Override
    public List<SysMenu> getAllMenu(){
        List<SysMenu> sysMenus = menuDao.getAllMenu();
        if (sysMenus!=null){
            return sysMenus;
        }else{
            return null;
        }
    }

    /**
     * 查询菜单
     * @param searchKey
     * @param searchValue
     * @return
     */
    @Override
    public  List<SysMenu> searchMenu(String searchKey,String searchValue) {
        Map<String, Object> map = new HashMap<>();
        String key[] = searchKey.split(",");
        String value[]=searchValue.split(",");
        //逐一赋值
        for (int i = 0; i < key.length; i++) {
            if(value.length>i)
                map.put(key[i],value[i]);
            else
                map.put(key[i],"");
        }
        //查找菜单
        List<SysMenu> sysMenus = menuDao.searchMenu(map);
        if(sysMenus!=null){
            return sysMenus;
        }else{
            return null;
        }
    }

    /**
     * 获得单纯菜单信息
     * @return
     */
    @Override
    public List<SysMenu> getMenu(){
        return menuDao.getMenu();
    }

    /**
     * 添加菜单
     * @param
     * @return
     */
    @Override
    public String addMenu(Map<String, Object> map) {

        if(!("0".equals(String.valueOf(map.get("parent_id"))))){
            map.put("icon","#");

        }else{
            map.put("target","_self");
            map.put("icon","fa fa-compass");
        }
        SysMenu menu_id = menuDao.getMenuByMenuId(String.valueOf(map.get("menu_id")));
        if(menu_id!=null){
            return "菜单编号重复";
        }
        SysMenu href = menuDao.getMenuByHref(String.valueOf(map.get("href")));
        if(href!=null){
            return "地址重复";
        }
        SysMenu title = menuDao.getMenuByTitle(String.valueOf(map.get("title")));
        if(title!=null){
            return "菜单名称重复";
        }
        SysMenu perms = menuDao.getMenuByPerms(String.valueOf(map.get("perms")));
        if(perms!=null){
            return "权限标识重复";
        }
        int addMenu = menuDao.addMenu(map);
        if(addMenu>0){
            return "添加成功";
        }
        else{
            return "添加失败";
        }

    }
}
