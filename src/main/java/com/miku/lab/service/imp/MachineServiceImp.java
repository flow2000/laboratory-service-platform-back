package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/8 16:42
 *@version:1.1
 */

import com.miku.lab.dao.MachineDao;
import com.miku.lab.dao.MachineImgDao;
import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_img;
import com.miku.lab.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MachineServiceImp implements MachineService {

    @Autowired
    private MachineDao machineDao;
    @Autowired
    private MachineImgDao machineImgDao;


    @Override
    public Object getAllMachine() {
       /* List<Machine> machines =  ;
        List<Machine_img> machine_imgs=;*/
        Map<String,Object> map = new HashMap<>();
        map.put("machine",machineDao.getAllMachine());
        map.put("machine_img", machineImgDao.getAllMachineImg());
        return map;
    }
}
