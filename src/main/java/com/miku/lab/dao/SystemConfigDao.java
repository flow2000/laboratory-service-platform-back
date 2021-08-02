package com.miku.lab.dao;

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @Description:    配置dao
 * @Author:         涛
 * @CreateDate:     2021/7/20 15:06
 * @UpdateUser:     涛
 * @UpdateDate:     2021/7/20 15:06
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Mapper
@Repository
public interface SystemConfigDao {

    List<Map> getAllSystemConfig();

    //获取所有的配置信息
    List<Map> getAllConfig();

    /**
     * 分页获取所有配置信息
     * @return
     */
    List<Map>getPageConfig(Map<String,Object> map);

    //获取配置详细信息
    Config getDetailConfiglById(String id);

    //更新配置信息
    int updateConfig(Map<String,Object>map);

    List<Config> searchConfig(Map<String,Object>map);            //获取查询数据

    List<Config> getSearchPageConfig(Map<String,Object> map);    //获取查询所有分页数据
}
