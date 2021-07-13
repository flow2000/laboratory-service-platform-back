package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/8 16:39
 *@version:1.1
 */

import com.miku.lab.entity.Machine;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineDao {
    List<Machine> getAllMachine();

    List<Machine> getPageMachine(int page,int limit);
}
