package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/8 16:39
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine;

import com.miku.lab.entity.Machine_sort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MachineDao {
    List<Machine> getAllMachine();

    //获取分页仪器信息
    List<Machine> getPageMachine(int page, int limit);

    //获取分页仪器分类
    List<Machine_sort> getPageMachineSort(Map<String, Object> map);

    //获取所有仪器分类
    List<Machine_sort> getAllMachineSort();

    //通过id获得分类信息
    Machine_sort getSortDetailById(String sortId);

    //更新仪器分类
    int updateMachineSort(Map<String, Object> map);

    //添加仪器分类
    int addMachineSort(Map<String, Object> map);

    //通过分类id删除分类
    int delMachineSort(String sortId);

    List<Machine_sort> searchSort(Map<String, Object> map);            //获取查询数据

    List<Machine_sort> getSearchPageSort(Map<String, Object> map);    //获取查询所有分页数据
}

