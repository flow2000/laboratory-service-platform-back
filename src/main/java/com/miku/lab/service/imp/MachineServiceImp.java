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
import com.miku.lab.util.Constant;
import com.miku.lab.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class MachineServiceImp implements MachineService {

    @Autowired
    private MachineDao machineDao;
    @Autowired
    private MachineImgDao machineImgDao;



    /**
     * 获取所有仪器接口
     * @return
     */
    @Override
    public Object getAllMachine() {
        Map<String,Object> map = new HashMap<>();
        List<Map> list = machineDao.getAllMachine();
        map.put("machines",list);
        map.put("count", list.size());
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
        int p = 0;
        int m = 10;
        try{
            p = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            m = Integer.parseInt(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<Machine> machineList = machineDao.getPageMachine(p,m); //获取仪器数据
        int count = machineDao.getMachineCount();
        map.put("machine",machineList);
        map.put("count",count);
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
        int m = Integer.valueOf(limit);
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

    /**
     * 更新仪器分类信息
     * @param machine_sort
     * @return
     */
    @Override
    public Object updateMachineSort(Machine_sort machine_sort) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",machine_sort.getId());
        map.put("sortId",machine_sort.getSortId());
        map.put("sortName",machine_sort.getSortName());
        map.put("remark",machine_sort.getRemark());
        map.put("validStatus",machine_sort.getValidStatus());
        map.put("updateTime",new Date());
        map.put("updater",machine_sort.getUpdater());

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

    /**
     * 添加仪器分类
     * @param machine_sort
     * @return
     */
    @Override
    public int addMachineSort(Machine_sort machine_sort) {
        Map<String,Object> map = new HashMap<>();
        map.put("sortId", IdUtil.geneId(Constant.BUSINESS_SORT));
        map.put("sortName",machine_sort.getSortName());
        map.put("remark",machine_sort.getRemark());
        map.put("validStatus","1");
        map.put("creater",machine_sort.getCreater());
        map.put("createTime",new Date());
        Machine_sort sort = machineDao.getSortDetailById(machine_sort.getSortId());
        if(sort==null){
            int addSort = machineDao.addMachineSort(map);
            if(addSort!=0){
                return 1;
            }
        }else{
            return 0;
        }
        return 0;
    }

    @Override
    public int delMachineSort(String sortId) {
        int delSort = machineDao.delMachineSort(sortId);
        if(delSort!=0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Object searchSort(String searchKey,String searchValue,String page, String limit){
        Map<String,Object> map = new HashMap<>();
        map.put("key",searchValue);
        map.put("value",searchKey);
        List<Machine_sort> searchAllSort = machineDao.searchSort(map);

        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        List<Machine_sort> machinePageList = machineDao.getSearchPageSort(map);
        if(machinePageList!=null){
            map.put("machine_sort",machinePageList);
            map.put("count",searchAllSort.size());
            return map;
        }else {
            return null;
        }
    }

    @Override
    public int addMachine(Map<String, Object> param) {
        String img_code = IdUtil.getSixNum();//生成六位随机图片编号
        Object machineImg = machineImgDao.getOneImg(img_code);
        while(machineImg!=null){
            machineImg = machineImgDao.getOneImg(img_code);
        }
        System.out.println(param);
        if(param.get("img_url")==null||param.get("img_url")==""){
            param.put("img_url",Constant.IMGURL); //如果没有上传图片则设置图片的默认值
        }
        param.put("img_code",img_code);
        int res = machineImgDao.addMachine_img(param);
        if(res==1){//插入图片成功时才执行
            return machineDao.addMachine(param);
        }
        return 0;
    }

    @Override
    public int updateMachine(Map<String, Object> param) {
        machineImgDao.updateMachine_img(param);
        return  machineDao.updateMachine(param);
    }

    @Override
    public int updateMachineCheck(Map<String, Object> param) {
        return machineDao.updateMachineCheck(param);
    }

    @Override
    public Object searchMachine(int page,int limit,String searchKey,String searchValue) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        String [] key = searchKey.split(",");
        String [] value = searchValue.split(",");
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        map.put("baseInfoKey",key[0]);
        map.put("statusKey",key[1]);
        map.put("baseInfoValue",value[0]);
        if(value.length>=2){
            map.put("statusValue",value[1]);
        }
        List<Map> pageMachineList = machineDao.searchMachine(map);
        int count = machineDao.getSearchMachineCount(map);
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("machines",pageMachineList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public int deleteMachine(Map<String, Object> param) {
        String machineStr = (String) param.get("id");
        String [] machineArr = machineStr.split(",");

        String imgStr = (String) param.get("img_code");
        String [] imgArr = imgStr.split(",");

        machineImgDao.deleteMachineImg(imgArr);
        return machineDao.deleteMachine(machineArr);
    }

}
