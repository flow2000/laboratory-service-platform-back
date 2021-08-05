package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 15:20
 *@version:1.1
 */

        import com.miku.lab.dao.SystemOperationDao;
        import com.miku.lab.entity.ArticleSort;
        import com.miku.lab.entity.LabInfo;
        import com.miku.lab.entity.Machine_sort;
        import com.miku.lab.entity.SystemOperation;
        import com.miku.lab.service.OperService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@Service
public class OperServiceImp implements OperService {

    @Autowired
    private SystemOperationDao  systemOperationDao;

    @Override
    public Object getAllOper() {
        List<SystemOperation> systemOperations = systemOperationDao.getAllOperation();
        if(systemOperations!=null){
            return systemOperations;
        }else{
            return null;
        }
    }

    /**
     * 分页获取仪器分类数据
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Object getPageOper(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        List<SystemOperation> systemOperations = systemOperationDao.getPageOper(map); //获取仪器数据
        List<SystemOperation> allOperation = systemOperationDao.getAllOperation();
        map.put("operations",systemOperations);
        map.put("count",allOperation.size());
        return map;
    }

    @Override
    public  Object searchOper(String searchKey,String searchValue,String page, String limit) {


        Map<String, Object>map = new HashMap<>();
        String key[] = searchKey.split(",");
        String value[]=searchValue.split(",");
        //逐一赋值
        for (int i = 0; i < key.length; i++) {
            if(value.length>i)
                map.put(key[i],value[i]);
            else
                map.put(key[i],"");
        }
        //设置分页
        setPageLimit(map,page,limit);
        //查找用户
        List<SystemOperation> systemOperations = systemOperationDao.searchOper(map);
        List<SystemOperation> searchPageOper = systemOperationDao.getSearchPageOper(map);
        if(systemOperations!=null){
            map.put("operations",searchPageOper);
            map.put("count",systemOperations.size());
            return map;
        }else{
            return null;
        }
    }
    //设置分页到map中
    public Map<String, Object>setPageLimit(Map<String, Object>map,String page,String limit){
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        return map;
    }

    @Override
    public int delOper(String id) {
        int delSort = systemOperationDao.delOper(id);
        if(delSort!=0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 添加日志
     * @param operation
     * @return
     */
    @Override
    public int addOper(SystemOperation operation) {
        Map<String,Object> map = new HashMap<>();
        map.put("ip_address",operation.getIpAddress());
        map.put("module_code",operation.getModuleCode());
        map.put("operator",operation.getOpertor());
        map.put("oper_time",new Date());
        map.put("oper_type",operation.getOperType());
        map.put("oper_content",operation.getOperContent());
        map.put("is_ok",operation.getIsOk());
        map.put("moduleName",operation.getModuleName());
        map.put("respResult",operation.getRespResult());
        int addAffect = systemOperationDao.addOper(map);
        if(addAffect!=0){
            return addAffect;
        }else{
            return 0;
        }

    }
}
