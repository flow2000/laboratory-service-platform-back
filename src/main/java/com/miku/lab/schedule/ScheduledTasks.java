package com.miku.lab.schedule;

import com.miku.lab.dao.TimerTaskDao;
import com.miku.lab.service.MailService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private TimerTaskDao timerTaskDao;

    @Autowired
    private MailService mailService;

    /**
     * 定时处理申请失效或者预约失效
     */
    @Scheduled(fixedRate = Constant.INTERVAL_BOOKING)
    public void freshExpireBooking() {
        log.info("定时处理申请失效任务 处理开始");
        List<Map> list = timerTaskDao.getExpireApplication(); //获取失效申请
        if(list.size()==0){//判断是否有失效申请
            log.info("无失效申请，定时任务 处理结束");
            return;
        }else{
            log.info("获取失效申请：");
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            String email = (String) map.get("email");
            String booking_code = (String) map.get("booking_code");
            log.info("失效申请编号：");
            log.info(booking_code);
            timerTaskDao.freshBooking(map); //设置申请状态为失效
            timerTaskDao.addBookingLog(map); //添加到申请记录表
            if(StringUtil.isEmail(email)){
                mailService.sendApplicationExpireMail(email,booking_code); //发送邮箱
            }else {
                log.info("申请人邮箱为空，无法发送");
            }
            log.info("已设置失效，已记录结果");
        }
        log.info("处理申请失效任务 处理结束");
    }

    /**
     * 定时刷新预约仪器
     */
    @Scheduled(fixedRate = Constant.INTERVAL_MACHINE)
    public void freshBookingMachine() {
        log.info("定时处理刷新预约仪器任务 处理开始");
        List<Map> list = timerTaskDao.getExpireMachine(); //获取失效仪器
        if(list.size()==0){//判断是否有过期仪器
            log.info("无过期仪器，定时任务 处理结束");
            return;
        }else{
            log.info("获取过期仪器编号：");
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            String machine_code = (String) map.get("machine_code");
            String booking_code = (String) map.get("booking_code");
            Integer machine_number = (Integer) map.get("machine_number");
            log.info(machine_code);
            timerTaskDao.freshBookingMachine(machine_code,String.valueOf(machine_number)); //增加仪器表的可预约数
            timerTaskDao.delWxBookingMachine(booking_code,machine_code);    //逻辑删除微信预约仪器
        }
        log.info("刷新预约仪器定时任务 处理结束");
    }

    /**
     * 定时刷新实验室
     */
    @Scheduled(fixedRate = Constant.INTERVAL_LAB)
    public void freshBookingLab() {
        log.info("定时处理刷新实验室任务 处理开始");
        List<Map> list = timerTaskDao.getExpireLab(); //获取失效实验室
        if(list.size()==0){//判断是否有过期实验室
            log.info("无过期实验室，定时任务 处理结束");
            return;
        }else{
            log.info("获取过期实验室编号：");
        }
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map = list.get(i);
            String lab_id = (String) map.get("lab_id");
            BigInteger id = (BigInteger) map.get("id");
            log.info(lab_id);
            timerTaskDao.freshBookingLab(lab_id); //将实验室设为可借用
            timerTaskDao.invalidOrderCheck(id);     //将审核数据逻辑删除
        }
        log.info("刷新实验室定时任务 处理结束");
    }

}
