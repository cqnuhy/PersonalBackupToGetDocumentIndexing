package com.dl.service.impl;

import java.util.List;
import java.util.Map;

import com.dl.dao.WordDetailDao;
import com.dl.entity.WordDetail;
import com.dl.service.WordDetailService;
import com.dl.utils.db.DBConnection;
import com.dl.utils.factory.DaoFactory;


public class WordDetailServiceImpl implements WordDetailService {

	@Override
	public List<WordDetail> findAll() {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(conn.getConnection());
		List<WordDetail> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = wordDetailDao.findAll();
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			wordDetailDao.colseResultSet();
			wordDetailDao.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findMap(String id) {
		DBConnection conn = new DBConnection();
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(conn.getConnection());
		List<Map<String, Object>> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = wordDetailDao.findMapByKey(id);
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			wordDetailDao.colseResultSet();
			wordDetailDao.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public int saveOrUpate(WordDetail wd) {
		DBConnection conn = new DBConnection();
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(conn.getConnection());
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = wordDetailDao.supdate(wd);
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			wordDetailDao.colseResultSet();
			wordDetailDao.colsePreparedStatement();
			conn.close();
		}
		return count;
	}

}
