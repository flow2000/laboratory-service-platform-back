package com.miku.lab.service.imp;

import com.miku.lab.dao.AppointmentDao;
import com.miku.lab.entity.Appointment;
import com.miku.lab.entity.BookBody;
import com.miku.lab.entity.BookLog;
import com.miku.lab.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: panghai
 * @Date: 2022/07/13/17:24
 * @Description: 预约记录业务实现类
 */
@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    public AppointmentDao appointmentDao;

    @Override
    public Map<String, Object> listAppointment(Appointment appointment) {
        Map<String, Object> resMap = new HashMap<>();
        int count = 0;
        Appointment app = (Appointment) appointment.clone();
        app.setPage(null);
        app.setLimit(null);
        List<Appointment> list = appointmentDao.listAppointment(app);
        for (int i = 0; i < list.size(); i++) {
            Appointment p = list.get(i);
            double total = 0.0;
            Date later = null;
            appointment.setMachineId(p.getMachineId());
            List<String> bookingCodeList = appointmentDao.selectBookingMachine(appointment);
            List<Appointment> appointments = new ArrayList<>();
            for (String bookingCode : bookingCodeList) {
                if (bookingCode != null && !"".equals(bookingCode)) {
                    Appointment ap = (Appointment) appointment.clone();
                    ap.setBookingCode(bookingCode);
                    appointments.add(ap);
                }
            }
            if (appointments.size() > 0) {
                count++;
                Map<String, Object> map = new HashMap<>();
                map.put("startTime", appointment.getStartTime());
                map.put("endTime", appointment.getEndTime());
                map.put("appointmentList", appointments);
                List<BookBody> bookBodyList = appointmentDao.selectBookList(map);
                for (BookBody bookBody : bookBodyList) {
                    if (bookBody.getStartTime() != null && bookBody.getEndTime() != null && bookBody.getOrderNumber() != null) {
                        int hour = getDifferHour(bookBody.getStartTime(), bookBody.getEndTime());
                        if (hour <= 4) {
                            total += 0.5 * bookBody.getOrderNumber();
                        } else {
                            int day = hour / 24;
                            if (hour % 24 != 0) {
                                day++;
                            }
                            total += day * bookBody.getOrderNumber();
                        }
                        if (later == null) {
                            later = bookBody.getStartTime();
                        } else {
                            if (later.before(bookBody.getStartTime())) {
                                later = bookBody.getStartTime();
                            }
                        }
                    }
                }
                p.setTotalTime(total);
                p.setLastTime(later);
                list.set(i, p);
                if (p.getTotalTime() != null && p.getMachineId() != null) {
                    appointmentDao.updateMachineTotalTime(p);
                }
            }
        }
        if (appointment.getStartTime() == null || appointment.getEndTime() == null) {
            resMap.put("count", list.size());
            resMap.put("list", list);
            return resMap;
        }
        count = 0;
        List<Appointment> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Appointment p = list.get(i);
            List<BookBody> bookBodyList = p.getBookBodyList();
            if (bookBodyList.size() == 0) {
                continue;
            }
            for (BookBody bookBody : bookBodyList) {
                if (bookBody.getStartTime() == null || bookBody.getEndTime() == null) {
                    continue;
                }
                if (bookBody.getStartTime().after(appointment.getStartTime())
                        && bookBody.getStartTime().before(appointment.getEndTime())
                        && bookBody.getEndTime().before(appointment.getEndTime())
                        && bookBody.getEndTime().after(appointment.getStartTime())) {
                    count++;
                    resList.add(p);
                    break;
                }
            }
        }
        resMap.put("count", count);
        resMap.put("list", resList);
        return resMap;
    }

    @Override
    public int listAppointmentNumber(Appointment appointment) {
        return appointmentDao.listAppointmentNumber(appointment);
    }

    @Override
    public List<BookBody> listBooking(Appointment appointment) {
        if (appointment.getMachineId() == null) {
            return new ArrayList<>();
        }
        List<String> bookingCodeList = appointmentDao.selectBookingMachine(appointment);
        List<Appointment> appointments = new ArrayList<>();
        for (String bookingCode : bookingCodeList) {
            if (bookingCode != null && !"".equals(bookingCode)) {
                Appointment ap = (Appointment) appointment.clone();
                ap.setBookingCode(bookingCode);
                appointments.add(ap);
            }
        }
        if (appointment.getPage() != null && appointment.getLimit() != null) {
            appointment.setPage((appointment.getPage() - 1) * appointment.getLimit());
            appointment.setLimit(appointment.getLimit());
        }
        Map<String, Object> map = new HashMap<>();
        if (appointments.size() > 0) {
            map.put("appointmentList", appointments);
            map.put("page", appointment.getPage());
            map.put("limit", appointment.getLimit());
            List<BookBody> list = appointmentDao.selectBookList(map);
            for (BookBody bookBody : list) {
                double total = 0.0;
                if (bookBody.getStartTime() != null && bookBody.getEndTime() != null && bookBody.getOrderNumber() != null) {
                    int hour = getDifferHour(bookBody.getStartTime(), bookBody.getEndTime());
                    if (hour <= 4) {
                        total += 0.5 * bookBody.getOrderNumber();
                    } else {
                        int day = hour / 24;
                        if (hour % 24 != 0) {
                            day++;
                        }
                        total += day * bookBody.getOrderNumber();
                    }
                }
                bookBody.setAppointmentTime(total);
            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public int listBookingNumber(Appointment appointment) {
        if (appointment.getMachineId() == null) {
            return 0;
        }
        List<String> bookingCodeList = appointmentDao.selectBookingMachine(appointment);
        List<Appointment> appointments = new ArrayList<>();
        for (String bookingCode : bookingCodeList) {
            if (bookingCode != null && !"".equals(bookingCode)) {
                Appointment ap = (Appointment) appointment.clone();
                ap.setBookingCode(bookingCode);
                appointments.add(ap);
            }
        }
        Map<String, Object> map = new HashMap<>();
        if (appointments.size() > 0) {
            map.put("appointmentList", appointments);
            return appointmentDao.selectBookListNumber(map);
        }
        return 0;
    }

    @Override
    public List<BookLog> listAudit(Appointment appointment) {
        if (appointment.getMachineId() == null) {
            return new ArrayList<>();
        }
        List<String> bookingCodeList = appointmentDao.selectBookingMachine(appointment);
        List<Appointment> appointments = new ArrayList<>();
        for (String bookingCode : bookingCodeList) {
            if (bookingCode != null && !"".equals(bookingCode)) {
                Appointment ap = (Appointment) appointment.clone();
                ap.setBookingCode(bookingCode);
                appointments.add(ap);
            }
        }
        if (appointment.getPage() != null && appointment.getLimit() != null) {
            appointment.setPage((appointment.getPage() - 1) * appointment.getLimit());
            appointment.setLimit(appointment.getLimit());
        }
        Map<String, Object> map = new HashMap<>();
        if (appointments.size() > 0) {
            map.put("appointments", appointments);
            map.put("page", appointment.getPage());
            map.put("limit", appointment.getLimit());
            return appointmentDao.selectAuditList(map);
        }
        return new ArrayList<>();
    }

    @Override
    public int listAuditNumber(Appointment appointment) {
        if (appointment.getMachineId() == null) {
            return 0;
        }
        List<String> bookingCodeList = appointmentDao.selectBookingMachine(appointment);
        List<Appointment> appointments = new ArrayList<>();
        for (String bookingCode : bookingCodeList) {
            if (bookingCode != null && !"".equals(bookingCode)) {
                Appointment ap = (Appointment) appointment.clone();
                ap.setBookingCode(bookingCode);
                appointments.add(ap);
            }
        }
        if (appointments.size() > 0) {
            return appointmentDao.selectAuditListNumber(appointments);
        }
        return 0;
    }

    private static int getDifferHour(Date startDate, Date endDate) {
        long dayM = 1000 * 24 * 60 * 60;
        long hourM = 1000 * 60 * 60;
        long differ = endDate.getTime() - startDate.getTime();
        long hour = differ % dayM / hourM + 24 * (differ / dayM);
        return Integer.parseInt(String.valueOf(hour));
    }

}
