package com.miku.lab.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.miku.lab.entity.Machine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineDao extends BaseMapper<Machine> {

}
