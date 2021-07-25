package com.miku.lab.entity;/*
 *@author 邓涛
 *@data 2021/7/25 18:31
 *@version:1.1
 */

import lombok.*;

import java.util.Date;

@Data
@ToString
public class SmsQuery {
    private String bizId;
    private String phoneNumber;
    private Date sendDate;
    private Long pageSize;
    private Long currentPage;
}
