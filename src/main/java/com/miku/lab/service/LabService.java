package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 13:44
 *@version:1.1
 */

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LabService {

    Object getAllLabInfo();

    Object getPageLab(String page, String limit);

    Object getOneLab(String lab_id);

    int updateLabInfo(Map<String, Object> param);

    int updateLabValid(Map<String, Object> param);

    int updateLabUsing(Map<String, Object> param);

    int addLab(Map<String, Object> param);

    int deleteLab(Map<String, Object> param);

    Object searchLab(int page, int limit, String searchKey, String searchValue);

}
