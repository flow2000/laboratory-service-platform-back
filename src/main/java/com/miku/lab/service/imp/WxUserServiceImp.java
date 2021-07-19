package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/12 20:50
 *@version:1.1
 */

import com.miku.lab.dao.WxUserDao;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.WxUser;
import com.miku.lab.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 更新微信用户详细信息
     * @param wxUser
     * @return
     */
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

    /**
     * 更新微信推送信息
     * @param openId
     * @param isReceptMsg
     * @param isReceptPush
     * @return
     */
    @Override
    public int updateWxPush(String openId,String isReceptMsg,String isReceptPush) {
        Map<String,Object> map = new HashMap<>();
        map.put("openId",openId);
        map.put("isReceptMsg",isReceptMsg);
        map.put("isReceptPush",isReceptPush);
        map.put("updateTime",new Date());
        int updateAffect = wxUserDao.updateWxPush(map);
        if (updateAffect!=0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 添加微信用户
     * @param openId
     * @param username
     * @return
     */
    @Override
    public int addWxUser(String openId,String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("openId",openId);
        map.put("username",username);
        map.put("createTime",new Date());
        WxUser user = wxUserDao.getWxUserById(openId);
        if(user==null){
            int addSort = wxUserDao.addWxUser(map);
            if(addSort!=0){
                return 1;
            }
        }else{
            return 0;
        }
        return 0;
    }
}
