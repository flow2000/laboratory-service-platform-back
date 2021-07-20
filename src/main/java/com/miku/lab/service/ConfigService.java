package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/20 14:42
 *@version:1.1
 */

import com.miku.lab.entity.Config;
import org.springframework.stereotype.Service;

@Service
public interface ConfigService {

    //获取所有的配置信息
    Object getAllConfig();

    //分页获取配置信息
    Object getPageConfig(String page, String limit);

    //获取单个设置信息
    Object getConfigDetail(String id);

    //更新配置信息
    Object updateConfig(Config config);
}
