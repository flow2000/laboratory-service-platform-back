package com.miku.lab.dao;/*
 *@author 邓涛
 *@data 2021/7/31 16:33
 *@version:1.1
 */

import com.miku.lab.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PermissionDao {

    //通过菜单id和角色id查找角色菜单
    public RoleMenu getRoleMenuByMenuId(Map<String, Object >map);

    //添加角色菜单
    public int addRoleMenu(Map<String, Object>map);

    //删除对应角色菜单表记录
    public int deleteRoleMenu(Map<String, Object>map);

    //获得原来的对应角色的权限菜单
    public List<RoleMenu> getRoleMenu(Map<String, Object>map);


}
