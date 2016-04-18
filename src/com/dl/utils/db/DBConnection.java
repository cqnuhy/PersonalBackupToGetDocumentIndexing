package com.dl.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dl.utils.Const;


/**
 * 
*
* 文件名称：MyJdbc.java<br>
* 摘要：JDBC工具类<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年6月29日<br>
 */
public class DBConnection {
	
	private final String DB_NAME = Const.applicationConst.getProperty("DB_NAME", "wordIndex");
	private final String DB_USER = Const.applicationConst.getProperty("DB_USER", "sa");
	private final String DB_PASSWORD = Const.applicationConst.getProperty("DB_PASSWORD", "123456");

	private final String JDBC_DRIVER = Const.applicationConst.getProperty("JDBC_DRIVER");
	private final String DB_URL = "jdbc:sqlserver://"+Const.SERVER_IP+":1433;DatabaseName="+DB_NAME;
	private Connection connection = null;

	/**
	 * 加载JDBC驱动
	 * @date 2015年6月29日 下午1:27:56
	 * @author 胡毅
	 * @return
	 */
	public DBConnection(){
		try {
			Class.forName(JDBC_DRIVER);
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 * @date 2015年6月29日 下午1:27:56
	 * @author 胡毅
	 * @return
	 */
	public Connection getConnection() {
		return this.connection;
	}
	
	/**
	 * 关闭JDBC连接
	 * @date 2015年6月29日 下午1:28:14
	 * @author 胡毅
	 */
	public void close(){
		if(this.connection != null){
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 是否自动提交事务
	 * @date 2015年6月29日 下午1:27:56
	 * @author 胡毅
	 * @return
	 */
	public void setAutoCommit(Boolean flag){
		try {
			this.connection.setAutoCommit(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 手动提交事务
	 * @date 2015年6月29日 下午1:27:56
	 * @author 胡毅
	 * @return
	 */
	public void setCommit(){
		try {
			this.connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setRollback(){
		try {
			this.connection.rollback();
			System.out.println("操作出错，事务回滚了");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
