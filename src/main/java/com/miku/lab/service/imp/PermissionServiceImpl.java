package com.miku.lab.service.imp;/*
 *@author 邓涛
 *@data 2021/7/31 16:34
 *@version:1.1
 */

import com.miku.lab.dao.PermissionDao;
import com.miku.lab.entity.RoleMenu;
import com.miku.lab.service.PermissionService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 修改权限菜单
     * @param resMap
     * @return
     */
    @Override
    //String menuIds,String roleId
    public int updatePermission(Map<String, Object>resMap) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", resMap.get("roleId"));
        String[] menus = (String.valueOf(resMap.get("menuIds"))).split(",");
        int count = menus.length;

        //删除曾经不同的菜单------start
        List<RoleMenu> checkOld = permissionDao.getRoleMenu(map);
        //旧的时候
        for(int i=0;i<checkOld.size();i++){
            //不相等的时候,以新的为主，旧的删除
            boolean flag= true;
            String menuId =checkOld.get(i).getMenuId();
            //检验新的是否都包含
            for (String menuCheck : menus) {
                if(menuId.equals(menuCheck)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                map.put("menuId",menuId);
                int delSuccess = permissionDao.deleteRoleMenu(map);
            }
        }

        //删除曾经不同的菜单------end


        //更新新的权限菜单------start
        for (String menu : menus) {
            map.put("menuId", menu);
            RoleMenu roleMenuByMenuId = permissionDao.getRoleMenuByMenuId(map);
            if (roleMenuByMenuId != null) {
                continue;
            } else {
                try {
                    int addAffect = permissionDao.addRoleMenu(map);//加入角色菜单记录
                } catch (Exception e) {
                    return 0;
                }
            }

        }
        return 1;
        //更新新的权限菜单------end
    }


}
