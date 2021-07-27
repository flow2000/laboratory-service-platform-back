package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 13:44
 *@version:1.1
 */

import com.miku.lab.dao.LabDao;
import com.miku.lab.entity.LabInfo;
import com.miku.lab.service.LabService;
import com.miku.lab.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LabServiceImp implements LabService {

    @Autowired
    private LabDao labDao;

    @Override
    public Object getAllLab() {
        List<Map> list = labDao.getAllLab();
        if(list!=null){
            return list;
        }else{
            return null;
        }
    }

    @Override
    public Object getPageLab(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            m = Integer.parseInt(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<Map> pageUserList = labDao.getPageLab(p,m);
        int count = labDao.getLabCount();
        map.put("labs",pageUserList);
        map.put("count",count);
        return map;
    }

    @Override
    public Object getOneLab(String lab_id) {
        if (lab_id==null||"".equals(lab_id)){
            return null;
        }
        Map<String,Object> map = labDao.getOneLab(lab_id);
        return map;
    }

    @Override
    public int updateLabInfo(Map<String, Object> param) {
        return labDao.updateLabInfo(param);
    }

    @Override
    public int updateLabValid(Map<String, Object> param) {
        return labDao.updateLabValid(param);
    }

    @Override
    public int updateLabUsing(Map<String, Object> param) {
        return labDao.updateLabUsing(param);
    }

    @Override
    public int addLab(Map<String, Object> param) {
        String lab_id = IdUtil.getSixNum(); //获取六位随机字符串类型的数字
        Map<String,Object> map = null;
        map = labDao.getOneLab(lab_id);
        if (map==null){
            param.put("lab_id",lab_id);
            return labDao.addLab(param);
        }
        return 0;
    }

    @Override
    public int deleteLab(Map<String, Object> param) {
        String str = (String) param.get("lab_id");
        String [] arr = str.split(",");
        return labDao.deleteLab(arr);
    }

    @Override
    public Object searchLab(int page, int limit, String searchKey, String searchValue) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        String [] key = searchKey.split(",");
        String [] value = searchValue.split(",");
        map.put("baseInfoKey",key[0]);
        map.put("statusKey",key[1]);
        map.put("baseInfoValue",value[0]);
        if(value.length>=2){
            map.put("statusValue",value[1]);
        }
        List<Map> pageLabList = labDao.searchLab(map);
        int count = labDao.searchLabCount(map);
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("labs",pageLabList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Object getOneBorrowedLab(String lab_id) {
        return labDao.getOneBorrowedLab(lab_id);
    }
}
