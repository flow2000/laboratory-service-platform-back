package com.miku.lab.dao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TimerTaskDao {

    List<Map> getExpireApplication();

    void freshBooking(Map<String, Object> map);

    void addBookingLog(Map<String, Object> map);

    List<Map> getExpireMachine();

    List<Map> getExpireLab();

    int freshBookingMachine(String machine_code, String machine_number);

    int freshBookingLab(String lab_id);
}
