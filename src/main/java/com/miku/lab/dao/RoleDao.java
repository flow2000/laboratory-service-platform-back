package com.miku.lab.dao;

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleDao {
    List<Map> getAllRole();

    int getTotalNumber();

    List<Map> getPageRole(int p, int m);

    Role getRoleDetailById(String roleCode);

    int updateRole(Map<String,Object>map);

    int addRole(Map<String,Object>map);

    int delRole(String roleCode);

    List<Role> searchRole(Map<String,Object>map);            //获取查询数据

    List<Role> getSearchPageRole(Map<String,Object> map);    //获取查询所有分页数据
}
