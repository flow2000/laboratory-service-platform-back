package com.miku.lab.service.imp;

import com.miku.lab.dao.StatisticsDao;
import com.miku.lab.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;



    @Override
    public Map<String, Object> getTotal() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("user_count",statisticsDao.getUserCount()+statisticsDao.getWxUserCount());
        map.put("machine_count",statisticsDao.getMachineCount());
        map.put("booking_count",statisticsDao.getBookingCount());
        map.put("article_count",statisticsDao.getArticleCount());
        return map;
    }

    @Override
    public List<Map> getMachineStatistics() {
        return statisticsDao.getMachineStatistics();
    }

    @Override
    public List<Map> getLabType() {
        return statisticsDao.getLabStatistics();
    }

    @Override
    public Map<String, Object> getSixMonthsStatistics() {
        Map<String,Object> resMap = new HashMap<>();
        Map<Object,Object> nameMap = new HashMap<>();
        nameMap.put("01","一月");nameMap.put("02","二月");nameMap.put("03","三月");
        nameMap.put("04","四月");nameMap.put("05","五月");nameMap.put("06","六月");
        nameMap.put("07","七月");nameMap.put("08","八月");nameMap.put("09","九月");
        nameMap.put("10","十月");nameMap.put("11","十一月");nameMap.put("12","十二月");

        List<Object> numberMonthList = getList(statisticsDao.getCountList("sys_user"),"months");
        List<Object> userCountList =  getList(statisticsDao.getCountList("sys_user"),"count");
        List<Object> wxUserCountList =  getList(statisticsDao.getCountList("wx_booking_user"),"count");
        List<Object> machineCountList = getList(statisticsDao.getCountList("sys_machine"),"count");
        List<Object> booingCountList = getList(statisticsDao.getCountList("sys_booking_log"),"count");
        List<Object> articleCountList = getList(statisticsDao.getCountList("sys_article"),"count");

        List<Object> ChineseMonthList = new ArrayList<>();
        for (int i = 0; i < numberMonthList.size(); i++) {
            ChineseMonthList.add(nameMap.get(numberMonthList.get(i)));
        }

        for (int i = 0; i < userCountList.size(); i++) {
            long v = (long) userCountList.get(i);
            if(wxUserCountList.size()>i){
                userCountList.set(i,v+(long)wxUserCountList.get(i));
            }
        }

        resMap.put("months",ChineseMonthList);
        resMap.put("user_count_list",userCountList);
        resMap.put("machine_count_list",machineCountList);
        resMap.put("booking_count_list",booingCountList);
        resMap.put("article_count_list",articleCountList);
        return resMap;
    }

    public static List<Object> getList(List<Map> mapList,String key){
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            list.add(mapList.get(i).get(key));
        }
        return list;
    }
}
