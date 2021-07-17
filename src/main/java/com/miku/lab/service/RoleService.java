package com.miku.lab.service;

import com.miku.lab.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {

    List<Map> getAllRole();

    Object getPageRole(String page, String limit);

    Object getRoleDetail(String roleCode);

    Object updateRole(Role role);

    int addRole(Role role);

    int delRole(String roleCode);

    Object searchRole(String searchKey,String searchValue,String page, String limit);
}
