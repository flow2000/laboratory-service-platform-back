package com.miku.lab.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface StatisticsDao {

    List<Map> getMachineStatistics();

    int getUserCount();

    int getMachineCount();

    int getBookingCount();

    int getArticleCount();

    List<Map> getLabStatistics();

    List<Map> getCountList(String tableName);
}
