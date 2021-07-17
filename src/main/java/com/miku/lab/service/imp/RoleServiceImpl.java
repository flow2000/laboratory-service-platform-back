package com.miku.lab.service.imp;

import com.miku.lab.dao.RoleDao;
import com.miku.lab.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
