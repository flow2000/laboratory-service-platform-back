package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 13:46
 *@version:1.1
 */

import com.miku.lab.entity.LabInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LabDao {

    List<Map> getAllLab();

    List<Map> getPageLab(int p, int m);

    int getLabCount();

    Map<String, Object> getOneLab(String lab_id);

    int updateLabInfo(Map<String, Object> param);

    int updateLabValid(Map<String, Object> param);

    int updateLabUsing(Map<String, Object> param);

    int addLab(Map<String, Object> param);

    int deleteLab(String[] arr);

    List<Map> searchLab(Map<String, Object> map);

    int searchLabCount(Map<String, Object> map);
}
