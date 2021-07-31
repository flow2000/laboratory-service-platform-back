package com.miku.lab.service;/*
 *@author 邓涛
 *@data 2021/7/31 16:34
 *@version:1.1
 */

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PermissionService {

    public int updatePermission(Map<String, Object> resMap);
}
