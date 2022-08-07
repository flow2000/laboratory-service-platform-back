package com.miku.lab.dao;

import com.miku.lab.entity.Appointment;
import com.miku.lab.entity.BookBody;
import com.miku.lab.entity.BookLog;

import java.util.List;
import java.util.Map;

/**
 * @Author: panghai
 * @Date: 2022/07/13/17:29
 * @Description: 预约记录Dao
 */
public interface AppointmentDao {
    /**
     * 获取预约记录
     * @param appointment 搜索参数
     * @return 预约记录列表
     */
    public List<Appointment> listAppointment(Appointment appointment);

    /**
     * 获取预约记录搜索数量
     * @param appointment 搜索参数
     * @return 预约记录列表数量
     */
    public int listAppointmentNumber(Appointment appointment);

    /**
     * 获取审核信息
     * @param map 搜索参数
     * @return 审核信息列表
     */
    public List<BookLog> selectAuditList(Map<String, Object> map);

    /**
     * 获取审核信息数量
     * @param appointments 搜索参数
     * @return 数量
     */
    public int selectAuditListNumber(List<Appointment> appointments);

    /**
     * 获取预约信息
     * @param map 搜索参数
     * @return 预约信息列表
     */
    public List<BookBody> selectBookList(Map<String,Object> map);

    /**
     * 获取预约信息数量
     * @param map 搜索参数
     * @return 数量
     */
    public int selectBookListNumber(Map<String,Object> map);

    /**
     * 获取预约仪器信息
     * @param appointment 搜索参数
     * @return 预约仪器信息列表
     */
    public List<String> selectBookingMachine(Appointment appointment);

    /**
     * 更新仪器总预约时长
     * @param appointment 仪器参数
     * @return 更新结果
     */
    public int updateMachineTotalTime(Appointment appointment);
}
