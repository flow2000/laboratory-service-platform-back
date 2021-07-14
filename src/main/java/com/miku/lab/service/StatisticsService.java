package com.miku.lab.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StatisticsService {

    Map<String, Object> getTotal();

    List<Map> getMachineStatistics();

    List<Map> getLabType();

    Map<String, Object> getSixMonthsStatistics();
}
