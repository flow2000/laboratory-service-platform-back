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
    @TableId(value = "id")
    private Integer id;
    @TableId(value = "machine_code")
    private String machine_code;
    @TableId(value = "img_code")
    private String img_code;
    @TableId(value = "class_code")
    private String class_code;
     @TableId(value = "machine_name")
    private String machine_name;
     @TableId(value = "location")
    private String location;
     @TableId(value = "active_count")
    private Integer active_count;
     @TableId(value = "bookable_count")
    private Integer bookable_count;
      @TableId(value = "unit_price")
    private BigDecimal unit_price;
     @TableId(value = "buy_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date buyDate;
     @TableId(value = "money")
    private BigDecimal money;
     @TableId(value = "factory")
    private String factory;
     @TableId(value = "quality_date")
    private Integer quality_date;
     @TableId(value = "model")
    private String model;
     @TableId(value = "machine_function")
    private String machine_function;
      @TableId(value = "abstract1")
    private String abstract1;
     @TableId(value = "remark")
    private String remark;
     @TableId(value = "creater")
    private String creater;
     @TableId(value = "modifyer")
    private String modifyer;
     @TableId(value = "create_time")
    private Integer create_time;
     @TableId(value = "modify_time")
    private Integer modify_time;
     @TableId(value = "delete_time")
    private Integer deleteTime;
     @TableId(value = "valid_status")
    private Integer valid_status;
     @TableId(value = "is_check")

    private Integer isCheck;
     @TableId(value = "supplier")
    private String supplier;
     @TableId(value = "contacter")
    private String contacter;
     @TableId(value = "phone")
    private String phone;
   @TableId(value = "life")
    private Integer life;
    private Integer isDeleted;

}
