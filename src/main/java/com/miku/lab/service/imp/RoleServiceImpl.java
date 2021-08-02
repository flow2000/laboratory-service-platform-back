package com.miku.lab.service.imp;

import com.miku.lab.dao.RoleDao;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.entity.Role;
import com.miku.lab.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<Map> getAllRole() {
        return roleDao.getAllRole();
    }

    /**
     * 分页获取角色信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Object getPageRole(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            m = Integer.parseInt(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        map.put("roles",roleDao.getPageRole(p,m));
        map.put("count",roleDao.getTotalNumber());
        return map;
    }

    /**
     * 获取分类详细
     * @param roleCode
     * @return
     */
    @Override
    public Object getRoleDetail(String roleCode) {
        Map<String,Object> map = new HashMap<>();
        Role role = roleDao.getRoleDetailById(roleCode);
        if(role!=null){
            map.put("roleDetail",role);
            return map;
        }else{
            map.put("roleDetail","查询失败");
            return map;
        }
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @Override
    public Object updateRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",role.getId());
        map.put("roleCode",role.getRoleCode());
        map.put("roleName",role.getRoleName());
        map.put("isDefaultRole",role.getIsDefaultRole());
        map.put("validStatus",role.getValidStatus());
        map.put("updateTime",new Date());
        map.put("updater",role.getUpdater());

        int updateSort = roleDao.updateRole(map);
        if(updateSort!=0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public int addRole(Role role) {
        Map<String,Object> map = new HashMap<>();
        map.put("roleCode",role.getRoleCode());
        map.put("roleName",role.getRoleName());
        map.put("isDefaultRole",role.getIsDefaultRole());
        map.put("validStatus",role.getValidStatus());
        map.put("creater",role.getCreater());
        map.put("createTime",new Date());
        Role roleDetailById = roleDao.getRoleDetailById(role.getRoleCode());
        if(roleDetailById==null){
            int addSort = roleDao.addRole(map);
            if(addSort!=0){
                return 1;
            }
        }else{
            return 0;
        }
        return 0;
    }

    /**
     * 删除角色
     * @param roleCode
     * @return
     */
    @Override
    public int delRole(String roleCode) {
        int delAffect = roleDao.delRole(roleCode);
        if(delAffect!=0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 查找角色
     * @param searchKey
     * @param searchValue
     * @param page
     * @param limit
     * @return
     */
    @Override
    public  Object searchRole(String searchKey,String searchValue,String page, String limit) {

        Map<String, Object>map = new HashMap<>();
        String key[] = searchKey.split(",");
        String value[]=searchValue.split(",");
        //逐一赋值
        for (int i = 0; i < key.length; i++) {
            if(value.length>i)
                map.put(key[i],value[i]);
            else
                map.put(key[i],"");
        }
        //设置分页
        setPageLimit(map,page,limit);
        //查找用户
        List<Role> roles = roleDao.searchRole(map);
        if(roles!=null){
            map.put("roles",roles);
            map.put("count",roles.size());
            return map;
        }else{
            return null;
        }
    }

    //设置分页到map中
    public Map<String, Object>setPageLimit(Map<String, Object>map,String page,String limit){
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        return map;
    }
}
