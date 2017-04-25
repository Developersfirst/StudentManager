package com.situ.student.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class jdbcUtil {
	
//	private static final String url = "jdbc:mysql://localhost:3306/java";
//	private static final String userName = "root";
//	private static final String password = "1314";
//	private static final String driverClass = "com.mysql.jdbc.Driver";
	
	private static final String url;
	private static final String userName;
	private static final String password;
	private static final String driverClass;
	
	
	//静态代码块
	//配置文件的内容到内存，输入流。
	static {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("./src/db.properties");	
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//取出配置文件的内容。
		url = properties.getProperty("url");
		userName = properties.getProperty("userName");
		password = properties.getProperty("password");
		driverClass = properties.getProperty("driverClass");
		
		// 1.加载驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		// 2.获取连接对象
		try {
			connection = DriverManager.getConnection(
					url,userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void close(Connection connection, Statement statement,ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
