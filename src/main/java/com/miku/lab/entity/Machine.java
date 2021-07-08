package com.miku.lab.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Machine {

    private Integer id;                         //id
    private String imgCode;                    //图片编号
    private String machineId;                  //仪器编号
    private String classCode;                  //所属分类
    private String machineName;                //仪器名称
    private String machineLocate;              //存放位置
    private Integer machineCount;               //现存数量
    private String machineFunction;            //性能
    private String machineSpecify;             //规格型号
    private String machineAbstract;            //摘要
    private String machineRemark;              //说明
    private String machineSupplier;            //供应商单位
    private String machineContacter;           //供应商联系人
    private String machineContacter_phone;     //供应商联系电话
    private Integer machineLife;                //寿命
    private Integer bookableCount;              //可预约数量
    private BigDecimal unitPrice;                  //单价
    private Date buyDate;                       //购买日期
    private BigDecimal totalMoney;             //购买金额
    private String factory;                 //生产厂家
    private Integer qualityTime;            //保质期
    private Integer validStatus;               //有效状态
    private Integer isCheck;                    //是否需要审核
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
