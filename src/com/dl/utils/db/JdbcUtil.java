package com.dl.utils.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class JdbcUtil {
	
	private final String DB_NAME = Const.applicationConst.getProperty("DB_NAME", "wordIndex");
	private final String DB_USER = Const.applicationConst.getProperty("DB_USER", "sa");
	private final String DB_PASSWORD = Const.applicationConst.getProperty("DB_PASSWORD", "123456");

	private final String JDBC_DRIVER = Const.applicationConst.getProperty("JDBC_DRIVER");
	private final String DB_URL = "jdbc:sqlserver://"+Const.SERVER_IP+":1433;DatabaseName="+DB_NAME;
	private Connection connection = null;
	private PreparedStatement statm = null;

	/**
	 * 初始化数据库连接
	 */
	public JdbcUtil(){
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
	 * 获取JDBC连接
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
	public void close(Connection conn){
		try {
			if(null != conn && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection conn){
		try {
			System.err.println("业务出错了，正在回滚...");
			conn.rollback();
			System.err.println("回滚完成。");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取SQL预处理对象
	 * @date 2016年4月13日 上午10:58:03
	 * @author 胡毅
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		this.statm = this.connection.prepareStatement(sql);
		return this.statm;
	}
	
	public void close(PreparedStatement statm,ResultSet rs){
		try {
			if(null != rs && !rs.isClosed()){
				rs.close();
			}
			if(null != statm && !statm.isClosed()){
				statm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
