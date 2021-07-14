package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/8 16:42
 *@version:1.1
 */

import com.miku.lab.dao.MachineDao;
import com.miku.lab.dao.MachineImgDao;
import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_img;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MachineServiceImp implements MachineService {

    @Autowired
    private MachineDao machineDao;
    @Autowired
    private MachineImgDao machineImgDao;
    Map<String,Object> map = new HashMap<>();


    @Override
    public Object getAllMachine() {
       /* List<Machine> machines =  ;
        List<Machine_img> machine_imgs=;*/
        Map<String,Object> map = new HashMap<>();
        map.put("machine",machineDao.getAllMachine());
        map.put("machine_img", machineImgDao.getAllMachineImg());
        return map;
    }

    @Override
    public Object getPageMachine(String page, String limit) {

        List<String> machineIdList = new ArrayList<String>();
        int p = 0;
        int m = 10;
        try{
            p = Integer.valueOf(page);
            m = Integer.valueOf(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<Machine> machineList = machineDao.getPageMachine(p,m); //获取仪器数据
        map.put("machine",machineList);
        for (int i = 0; i < machineList.size(); i++) {
            machineIdList.add(machineList.get(i).getMachineId());
        }
        map.put("machine_img",machineImgDao.getPageMachineImg(machineIdList)); //获取仪器对应图片数据
        return map;
    }

    /**
     * 分页获取仪器分类数据
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Object getPageMachineSort(String page, String limit) {
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit)*(Integer.valueOf(page)-1+1);
        map.put("page",p);
        map.put("limit",m);
        List<Machine_sort> machineList = machineDao.getPageMachineSort(map); //获取仪器数据
        List<Machine_sort> machineSortAll = machineDao.getAllMachineSort();
        map.put("machine_sort",machineList);
        map.put("count",machineSortAll.size());
        return map;
    }
}
