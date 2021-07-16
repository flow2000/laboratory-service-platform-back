package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/12 20:50
 *@version:1.1
 */

import com.miku.lab.dao.WxUserDao;
import com.miku.lab.entity.WxUser;
import com.miku.lab.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxUserServiceImp implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Override
    public Object getAllWxUser() {
        List<WxUser> wxUserList = wxUserDao.getAllWxUser();
        if (wxUserList!=null){
            return wxUserList;
        }else{
            return null;
        }
    }

    @Override
    public int updateWxUser(WxUser wxUser) {
        wxUser.setUpdateTime(new Date());
        int addAffect = wxUserDao.updateWxUser(wxUser);
        if (addAffect!=0){
            return 1;
        }else {
            return 0;
        }


    }
}
