package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/9 14:17
 *@version:1.1
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class BookLog {

    private Integer id;                         //id

    private String bookingCode;                 //预约申请编号

    private String bookingChecker;              //审核人

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date checkTime;                     //审核时间

    private Integer checkResult;                //审核结果

    private Integer isPush;                     //是否推送信息

    private Integer validStatus;                //有效状态

    private String creater;                     //创建者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间
    private String delFlag;                    //删除标志
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date delTime;                      //删除时间

}
