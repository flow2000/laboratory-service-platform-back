package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/8 16:42
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    Object getAllMachine();
}
