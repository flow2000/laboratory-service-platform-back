package com.miku.lab.entity;

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
public class Role {
    //角色id
    private Integer id;
    //角色编号
    private String roleCode;
    //角色名称
    private String roleName;
    //是否是默认角色
    private Integer isDefaultRole;
    //有效状态
    private  Integer validStatus;
    //创建者
    private String creater;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //修改人
    private String updater;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    //删除标志
    private int isDisable;
    //删除时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date udeleteTime;
}
