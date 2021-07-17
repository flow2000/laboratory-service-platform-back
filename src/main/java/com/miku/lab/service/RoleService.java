package com.miku.lab.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {

    List<Map> getAllRole();

    Object getPageRole(String page, String limit);
}
