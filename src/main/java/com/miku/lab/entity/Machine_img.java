package com.miku.lab.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
/*@TableName(value = "sys_machine_img")*/
public class Machine_img {

    private Integer id;                         //id

    private String imgUrl;                      //图片地址

    private Integer validStatus;                //有效状态

    private String machineCode;                 //仪器编号

    private Integer is_tar;                     //是否是缩略图

    private String creater;                     //创建者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                    //创建时间

    private String updater;                      //更新者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                    //更新时间

    private String delFlag;                     //删除标志

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date delTime;                       //删除时间


}
