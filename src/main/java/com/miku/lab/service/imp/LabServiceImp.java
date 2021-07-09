package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 13:44
 *@version:1.1
 */

import com.miku.lab.dao.LabDao;
import com.miku.lab.entity.LabInfo;
import com.miku.lab.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImp implements LabService {

    @Autowired
    private LabDao labDao;

    @Override
    public Object getAllLabInfo() {
        List<LabInfo> labInfoList = labDao.getLabList();
        if(labInfoList!=null){
            return labInfoList;
        }else{
            return null;
        }
    }
}
