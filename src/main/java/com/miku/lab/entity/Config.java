package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/20 14:38
 *@version:1.1
 */


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
public class Config {

    //id
    @ApiModelProperty(hidden=true)
    private Integer  id;
    //编号
    @ApiModelProperty(value="配置编号")
    private String code;
    //名称
    @ApiModelProperty(hidden=true)
    private String name;
    //备注
    private String remark;
    //值
    @ApiModelProperty(value="对应值")
    private int value;
    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    @ApiModelProperty(hidden=true)
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间


}
