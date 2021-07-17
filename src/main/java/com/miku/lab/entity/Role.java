package com.miku.lab.entity;

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
public class Role {
    //角色id
    private Integer id;
    //角色编号
    private String roleCode;
    //角色名称
    private String roleName;
    //是否是默认角色
    private String isDefaultRole;
    //有效状态
    private  Integer validStatus;
    //创建者
    @ApiModelProperty(hidden=true)
    private String creater;                     //创建者

    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    @ApiModelProperty(hidden=true)
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(hidden=true)
    private Date updateTime;                   //更新时间
}
