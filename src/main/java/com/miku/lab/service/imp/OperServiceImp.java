package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 15:20
 *@version:1.1
 */

import com.miku.lab.dao.SystemOperationDao;
import com.miku.lab.entity.LabInfo;
import com.miku.lab.entity.SystemOperation;
import com.miku.lab.service.OperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperServiceImp implements OperService {

    @Autowired
    private SystemOperationDao  systemOperationDao;

    @Override
    public Object getAllOper() {
        List<SystemOperation> systemOperations = systemOperationDao.getAllOperation();
        if(systemOperations!=null){
            return systemOperations;
        }else{
            return null;
        }
    }
}
