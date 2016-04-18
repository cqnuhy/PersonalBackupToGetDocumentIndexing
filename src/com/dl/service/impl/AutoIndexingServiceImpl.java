package com.dl.service.impl;

import java.util.List;
import java.util.Map;

import com.dl.dao.AutoIndexingDao;
import com.dl.entity.AutoIndexing;
import com.dl.service.AutoIndexingService;
import com.dl.utils.db.DBConnection;
import com.dl.utils.factory.DaoFactory;

public class AutoIndexingServiceImpl implements AutoIndexingService {

	@Override
	public List<AutoIndexing> findAll() {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		AutoIndexingDao aid = DaoFactory.getAutoIndexingDao(conn.getConnection());
		List<AutoIndexing> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = aid.findAll();
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			aid.colseResultSet();
			aid.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveOrUpate(AutoIndexing wd) {
		DBConnection conn = new DBConnection();
		AutoIndexingDao aid= DaoFactory.getAutoIndexingDao(conn.getConnection());
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = aid.supdate(wd);
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			aid.colseResultSet();
			aid.colsePreparedStatement();
			conn.close();
		}
		return count;
	}

}
