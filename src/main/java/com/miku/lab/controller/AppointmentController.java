package com.miku.lab.controller;

import com.miku.lab.entity.Appointment;
import com.miku.lab.entity.BookBody;
import com.miku.lab.entity.BookLog;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.AppointmentService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: panghai
 * @Date: 2022/07/13/17:24
 * @Description: 预约记录接口
 */

@RestController
@RequestMapping("/appointment")
@Api(value = "AppointmentController", tags = "预约记录接口")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * 获取仪器
     *
     * @return 仪器列表
     */
    @ApiOperation(value = "获取仪器列表")
    @GetMapping("/getAppointment")
    public ReturnResult getAppointment(Appointment appointment) {
        if (appointment.getPage() != null && appointment.getLimit() != null) {
            appointment.setPage((appointment.getPage() - 1) * appointment.getLimit());
            appointment.setLimit(appointment.getLimit());
        }
        Map<String, Object> map = appointmentService.listAppointment(appointment);
        int count = (int) map.get("count");
        List<Appointment> list = (List<Appointment>) map.get("list");
        if (appointment.getPage() == null || appointment.getLimit() == null) {
            for (int i = 0; i < list.size(); i++) {
                Appointment a = list.get(i);
                a.setBookLogList(appointmentService.listAudit(a));
                a.setBookBodyList(appointmentService.listBooking(a));
            }
            return AjaxUtil.success(list, Constant.RESCODE_SUCCESS_MSG, count);
        }
        int page = appointment.getPage();
        int limit = appointment.getLimit();
        try {
            if (page > list.size()) {
                return AjaxUtil.success(new ArrayList<>(), Constant.RESCODE_SUCCESS_MSG, count);
            } else if (page + limit > list.size() || list.size() < limit) {
                return AjaxUtil.success(list.subList(page, count), Constant.RESCODE_SUCCESS_MSG, count);
            } else {
                return AjaxUtil.success(list.subList(page, page + limit), Constant.RESCODE_SUCCESS_MSG, count);
            }
        } catch (Exception e) {
            return AjaxUtil.success(new ArrayList<>(), Constant.RESCODE_SUCCESS_MSG, count);
        }
    }

    /**
     * 获取预约记录
     *
     * @return 预约记录列表
     */
    @ApiOperation(value = "获取预约记录")
    @GetMapping("/getBooking")
    public ReturnResult getBooking(Appointment appointment) {
        List<BookBody> appointmentList = appointmentService.listBooking(appointment);
        int count = appointmentService.listBookingNumber(appointment);
        return AjaxUtil.success(appointmentList, Constant.RESCODE_SUCCESS_MSG, count);
    }

    /**
     * 获取审核记录
     *
     * @return 预约审核列表
     */
    @ApiOperation(value = "获取审核记录")
    @GetMapping("/getAudit")
    public ReturnResult getAudit(Appointment appointment) {
        List<BookLog> appointmentList = appointmentService.listAudit(appointment);
        int count = appointmentService.listAuditNumber(appointment);
        return AjaxUtil.success(appointmentList, Constant.RESCODE_SUCCESS_MSG, count);
    }



}
