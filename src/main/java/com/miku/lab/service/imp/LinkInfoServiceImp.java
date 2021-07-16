package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/16 15:43
 *@version:1.1
 */

import com.miku.lab.dao.LinkInfoDao;
import com.miku.lab.entity.LinkInfo;
import com.miku.lab.entity.Suggestion;
import com.miku.lab.service.LinkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkInfoServiceImp implements LinkInfoService{

    @Autowired
    private LinkInfoDao linkInfoDao;

    @Override
    public Object getLinkInfoList() {
        List<LinkInfo> linkInfoList = linkInfoDao.getLinkInfoList();
        if(linkInfoList!=null){
            return linkInfoList;
        }else{
            return null;
        }
    }


}
