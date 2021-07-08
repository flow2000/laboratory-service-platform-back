package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/8 16:42
 *@version:1.1
 */

import com.miku.lab.dao.MachineDao;
import com.miku.lab.entity.Machine;
import com.miku.lab.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImp implements MachineService {

    @Autowired
    private MachineDao machineDao;


    @Override
    public List<Machine> getAllMachine() {
        return machineDao.getAllMachine();
    }
}
