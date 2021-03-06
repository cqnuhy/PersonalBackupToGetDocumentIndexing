package com.dl.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dl.dao.WordDetailDao;
import com.dl.entity.WordDetail;
import com.dl.service.WordDetailService;
import com.dl.utils.db.JdbcUtil;
import com.dl.utils.factory.DaoFactory;


public class WordDetailServiceImpl implements WordDetailService {

	@Override
	public List<WordDetail> findAll() {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(jdbcutil);
		List<WordDetail> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = wordDetailDao.findAll();
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//回滚事务
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findMap(String id) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(jdbcutil);
		List<Map<String, Object>> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = wordDetailDao.findMapByKey(id);
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//回滚事务
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return list;
	}

	@Override
	public int saveOrUpate(WordDetail wd) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		WordDetailDao wordDetailDao = DaoFactory.getWordDetailDao(jdbcutil);
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = wordDetailDao.supdate(wd);
			//提交事务
			conn.commit();
		} catch (Exception e) {
			//回滚事务
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return count;
	}

}
