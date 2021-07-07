package com.miku.lab.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Constant {
	
	public static String JDBC_Driver = "com.mysql.jdbc.Driver";
	public static String USERNAME = "root";
	public static String PASSWORD = "526500siji";				
	public static String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=UTF-8&useSSL=false";
	public static String ContentType = "text/json;charset=utf-8";

    /**
     * 数据请求返回码
     * */
    public static final int RESCODE_SUCCESS = 1000;         //成功
    public static final int RESCODE_SUCCESS_MSG = 1001;         //成功(有返回信息)
    public static final int RESCODE_EXCEPTION = 1002;         //请求抛出异常
    public static final int RESCODE_NOLOGIN = 1003;         //未登录状态
    public static final int RESCODE_NOEXIST = 1004;         //查询结果为空
    public static final int RESCODE_NOAUTH = 1005;         //无操作权限
    public static final int RESCODE_LOGINEXPIRE = 1006;         //登录过期
    public static final int RESCODE_INSERTERROR = 1007;         //插入失败
    public static final int RESCODE_MODIFYERROR= 1008;         //修改失败
    public static final int RESCODE_DELETEERROR = 1010;         //删除失败
    public static final int RESCODE_CAPTCHA_ERROR = 1011;         //验证码错误
    public static final int RESCODE_LOGIN_ERROR = 1012;         //未登录
    public static final int RESCODE_PASSWORD_ERROR = 1013;         //未登录
    public static final int RESCODE_REDIS_SUCCESS = 1014;         //查询redis成功
    public static final int RESCODE_REDIS_TIME_OUT = 1015;         //查询redis成功
    /**
     * token
     * */
    public static final String JWT_ID = "miku";
    public static final String JWT_SECRET = "miku-web";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
    public static final String JWT_ISSSUER = "119583010135";
   
    /**
     * redis
     */
    public static final String REDIS_HOST = "47.118.55.237";  //主机ip
    public static final int REDIS_PORT = 6379;                //端口号
    public static final int REDIS_EXPIRE_TIME = 60*30;        //设置TTL为30分钟
    public static final int REDIS_MAX_ACTIVE = 1024;          //最大连接数
    public static final int REDIS_MAX_IDLE = 100;             //最大空闲连接数
    public static final long REDIS_MAX_WAIT = 1000*10;        //获取可用连接的最大等待时间
    public static final int REDIS_TIMEOUT = 1000*10;		 //过期时间
    public static final boolean TEST_ON_BORROW = true;         //清空
    
    /**
     * 借阅时间
     */
    public static final Long STUDENT_EXPIRETIME = (long) (60*60*24*30*2);
    public static final Long TEACHER_EXPIRETIME = (long) (60*60*24*30*3);
    public static final Long OTHER_EXPIRETIME = (long) (60*60*24*30*1);
    
    /**
     * 验证码
     */
    public static String CODE = "";
    
    static public void ReturnType(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    
   /* *//**
     * 权限
     *//*
    public static final int PRI_INFOLIET = 0;    //信息列表权限
    public static final int PRI_PERMISION = 1;   //权限管理
    public static final int PRI_DEPARTMENT = 2;  //部门管理权限
    public static final int PRI_SALARY = 3;      //薪资核对权限
    public static final int PRI_PUNISH = 4;      //奖惩管理权限

    *//**
     * 用户
     *//*
    public static final int YEAR = 2021;   //当前年份
*/
}
