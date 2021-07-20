package com.miku.lab.service;

import com.miku.lab.entity.Config;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SystemConfigService {

    List<Map> getAllSystemConfig();

    //获取所有的配置信息
    Object getAllConfig();

    //分页获取配置信息
    Object getPageConfig(String page, String limit);

    //获取单个设置信息
    Object getConfigDetail(String id);

    //更新配置信息
    Object updateConfig(Config config);

    //查询配置信息
    Object searchConfig(String searchKey,String searchValue,String page, String limit);
}
