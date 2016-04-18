package com.dl.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dl.dao.AutoIndexingDao;
import com.dl.entity.AutoIndexing;
import com.dl.service.AutoIndexingService;
import com.dl.utils.db.JdbcUtil;
import com.dl.utils.factory.DaoFactory;

public class AutoIndexingServiceImpl implements AutoIndexingService {

	@Override
	public List<AutoIndexing> findAll() {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection(); 
		AutoIndexingDao aid = DaoFactory.getAutoIndexingDao(jdbcutil);
		List<AutoIndexing> list = null;
		try {
			conn.setAutoCommit(false);
			list = aid.findAll();
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
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
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection(); 
		AutoIndexingDao aid= DaoFactory.getAutoIndexingDao(jdbcutil);
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = aid.supdate(wd);
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return count;
	}

}
