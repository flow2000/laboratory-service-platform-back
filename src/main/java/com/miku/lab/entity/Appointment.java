package com.miku.lab.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/13/17:09
 * @Description: 预约记录实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Appointment implements Cloneable {

    /**
     * 仪器id
     */
    private String machineId;

    /**
     * 仪器名字
     */
    private String machineName;

    private String bookingCode;

    /**
     * 总时长
     */
    private Double totalTime;

    /**
     * 最近使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastTime;

    /**
     * 审核信息
     */
    private List<BookLog> bookLogList = new ArrayList<>();
    /**
     * 预约信息
     */
    private List<BookBody> bookBodyList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String username;
    private String labName;


    private Integer page;
    private Integer limit;

    @Override
    public Object clone() {
        Appointment ap = null;
        try {
            ap = (Appointment) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ap;
    }


}
