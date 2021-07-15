package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/8 16:42
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {
    Object getAllMachine();

    Object getPageMachine(String page, String limit);


    Object getPageMachineSort(String page, String limit);

    Object getSortDetail(String sortId);

    Object updateMachineSort(Machine_sort machine_sort);

    List<Machine_sort> getMachineType();
}
