package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/8 19:46
 *@version:1.1
 */

import com.miku.lab.entity.Machine_img;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MachineImgDao {

    List<Machine_img> getAllMachineImg();

    List<Machine_img> getPageMachineImg(@Param("machineIdList")List<String> machineIdList);

    Object getOneImg(String img_code);

    int addMachine_img(Map<String, Object> param);

    int updateMachine_img(Map<String, Object> param);

    int deleteMachineImg(String[] imgArr);
}
