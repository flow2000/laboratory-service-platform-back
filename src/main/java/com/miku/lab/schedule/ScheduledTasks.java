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
     * 每30分钟处理一次申请失效
     */
    @Scheduled(fixedRate = Constant.TIMEINTERVAL)
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
        log.info("定时任务 处理结束");
    }
}
