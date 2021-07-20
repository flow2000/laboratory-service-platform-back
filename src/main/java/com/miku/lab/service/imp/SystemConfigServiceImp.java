package com.miku.lab.service.imp;

import com.miku.lab.dao.SystemConfigDao;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Config;
import com.miku.lab.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SystemConfigServiceImp implements SystemConfigService {

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Override
    public List<Map> getAllSystemConfig() {
        return systemConfigDao.getAllSystemConfig();
    }


    @Override
    public Object getAllConfig() {
        List<Config> list = systemConfigDao.getAllConfig();
        if(list!=null){
            return list;
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
    public Object getPageConfig(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        List<Config> configs = systemConfigDao.getPageConfig(map); //获取分页仪器数据
        List<Config> allconfigs = systemConfigDao.getAllConfig();
        map.put("configs",configs);
        map.put("count",allconfigs.size());
        return map;
    }

    /**
     * 获取分类详细
     * @param id
     * @return
     */
    @Override
    public Object getConfigDetail(String id) {
        Map<String,Object> map = new HashMap<>();
        Config config = systemConfigDao.getDetailConfiglById(id);
        if(config!=null){
            map.put("configDetail",config);
            return map;
        }else{
            map.put("sortDetail","查询失败");
            return map;
        }
    }

    @Override
    public Object updateConfig(Config config) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",config.getId());
        map.put("code",config.getCode());
        map.put("name",config.getName());
        int updateConfig = systemConfigDao.updateConfig(map);
        if(updateConfig!=0){
            return "更新成功";
        }else{
            return "更新失败";
        }
    }

    @Override
    public  Object searchConfig(String searchKey,String searchValue,String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        map.put("key",searchValue);
        map.put("value",searchKey);
        List<Config> searchAllConfig = systemConfigDao.searchConfig(map);

        int p = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
        int m = Integer.valueOf(limit);
        map.put("page",p);
        map.put("limit",m);
        List<Config> configs = systemConfigDao.getSearchPageConfig(map);
        if(configs!=null){
            map.put("configs",configs);
            map.put("count",searchAllConfig.size());
            return map;
        }else {
            return null;
        }
    }
}
