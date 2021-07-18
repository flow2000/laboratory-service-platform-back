package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 15:20
 *@version:1.1
 */

import com.miku.lab.entity.SystemOperation;
import org.springframework.stereotype.Service;

@Service
public interface OperService {
    Object getAllOper();

    Object getPageOper(String page, String limit);

    Object searchOper(String searchKey,String searchValue,String page, String limit);

    int delOper(String id);
    int addOper(SystemOperation operation);
}
