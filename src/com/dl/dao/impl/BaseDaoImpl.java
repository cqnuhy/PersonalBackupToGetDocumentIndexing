package com.dl.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dl.dao.BaseDao;


public class BaseDaoImpl<T,ID extends Serializable> implements BaseDao<T, ID>{

	private PreparedStatement statm;// 预处理sql
	private ResultSet rs;// 结果集
	private Boolean flag;
	private Connection conn;
	
	/**
	 * 初始化BaseDaoImpl，传入连接对象
	 * @date 2015年6月30日 上午11:53:12
	 * @author 胡毅
	 * @param conn
	 */
	public void initBaseDaoImpl(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * 生成PreparedStatement对象
	 * @date 2015年6月30日 上午11:53:44
	 * @author 胡毅
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		this.statm = this.conn.prepareStatement(sql);
		return this.statm;
	}
	
	/**
	 * 生成查询ResultSet对象
	 * @date 2015年6月30日 上午11:57:13
	 * @author 胡毅
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet getResultSet() throws SQLException {
		this.rs = this.statm.executeQuery();
		return this.rs;
	}
	
	public boolean execute() throws SQLException {
		System.out.println(this.flag);
		this.flag = this.statm.execute();
		return this.flag;
	}
	
	public int executeUpdate() throws SQLException{
		return this.statm.executeUpdate();
	}
	
	public boolean execute(String sql) throws SQLException {
		this.flag = this.statm.execute(sql);
		return this.flag;
	}

	/**
	 * 关闭ResultSet
	 * @date 2015年6月30日 上午11:57:31
	 * @author 胡毅
	 */
	@Override
	public void colseResultSet() {
		if(this.rs != null){
			try {
				this.rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭PreparedStatement
	 * @date 2015年6月30日 上午11:57:46
	 * @author 胡毅
	 */
	@Override
	public void colsePreparedStatement() {
		if(this.statm != null){
			try {
				if(!this.statm.isClosed()){
					this.statm.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public List<T> findAll() throws Exception {
		
		return null;
	}

	@Override
	public List<T> findOne(String id) throws Exception {
		return null;
	}
}
