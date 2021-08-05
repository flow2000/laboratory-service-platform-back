package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/9 15:12
 *@version:1.1
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SystemOperation {

    private Integer id;             //id

    private String ipAddress;       //ip地址

    private BigInteger moduleCode;  //模块路径

    private String opertor;         //操作人

    private String moduleName;         //模块名称

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date operTime;          //操作时间

    private String operType;        //操作类型

    private String operContent;     //操作内容

    private String  isOk;    //操作成功否

    private String respResult;  //返回数据


}
