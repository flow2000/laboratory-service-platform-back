package com.miku.lab.service;

import com.miku.lab.entity.Appointment;
import com.miku.lab.entity.BookBody;
import com.miku.lab.entity.BookLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: panghai
 * @Date: 2022/07/13/17:24
 * @Description: 预约记录Service
 */
@Service
public interface AppointmentService {

    /**
     * 获取仪器
     * @param appointment 搜索参数
     * @return 仪器列表
     */
    public Map<String,Object> listAppointment(Appointment appointment);

    /**
     * 获取仪器搜索数量
     * @param appointment 搜索参数
     * @return 仪器列表数量
     */
    public int listAppointmentNumber(Appointment appointment);

    /**
     * 获取预约记录
     * @param appointment 搜索参数
     * @return 预约记录列表
     */
    public List<BookBody> listBooking(Appointment appointment);

    /**
     * 获取预约记录搜索数量
     * @param appointment 搜索参数
     * @return 预约记录列表数量
     */
    public int listBookingNumber(Appointment appointment);

    /**
     * 获取审核记录
     * @param appointment 搜索参数
     * @return 审核记录列表
     */
    public List<BookLog> listAudit(Appointment appointment);

    /**
     * 获取审核记录搜索数量
     * @param appointment 搜索参数
     * @return 审核记录列表数量
     */
    public int listAuditNumber(Appointment appointment);

}
