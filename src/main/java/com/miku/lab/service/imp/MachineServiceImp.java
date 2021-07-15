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



    /**
     * 获取所有的仪器接口
     * @return
     */
    @Override
    public Object getAllMachine() {
       /* List<Machine> machines =  ;
        List<Machine_img> machine_imgs=;*/
        Map<String,Object> map = new HashMap<>();
        map.put("machine",machineDao.getAllMachine());
        map.put("machine_img", machineImgDao.getAllMachineImg());
        return map;
    }

    /**
     * 分页获取仪器
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Object getPageMachine(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
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
        Map<String,Object> map = new HashMap<>();
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

    /**
     * 获取分类详细
     * @param sortId
     * @return
     */
    @Override
    public Object getSortDetail(String sortId) {
        Map<String,Object> map = new HashMap<>();
        Machine_sort machine_sort = machineDao.getSortDetailById(sortId);
        if(machine_sort!=null){
            map.put("machineDetail",machine_sort);
            return map;
        }else{
            map.put("machineDetail","查询失败");
            return map;
        }
    }

    @Override
    public Object updateMachineSort(Machine_sort machine_sort) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",machine_sort.getId());
        map.put("sortId",machine_sort.getSortId());
        map.put("sortName",machine_sort.getSortName());
        map.put("remark",machine_sort.getRemark());
        map.put("validStatus",machine_sort.getValidStatus());

        int updateSort = machineDao.updateMachineSort(map);
        if(updateSort!=0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }

    @Override
    public List<Machine_sort> getMachineType() {
        return machineDao.getAllMachineSort();
    }
}
