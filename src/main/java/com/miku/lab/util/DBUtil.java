package com.miku.lab.util;

import java.sql.*;

public class DBUtil {
	
	public static Connection connection = null;
	public static PreparedStatement st = null;
	static {
		try {
			Class.forName(Constant.JDBC_Driver);
			connection = DriverManager.getConnection(Constant.URL,Constant.USERNAME,Constant.PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接数据库失败");
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * 准备语句
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			st = connection.prepareStatement(sql);
		}catch(SQLException e) {
			System.out.println("初始化实例对象失败！！");
			e.printStackTrace();
		}
		if(st == null) {
			System.out.println("实例对象为空！！！");
		}
		return st;
	}
}
