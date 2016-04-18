package com.dl.service.impl;

import java.sql.Connection;
import java.util.List;

import com.dl.dao.EnterpriseInfoDao;
import com.dl.entity.indexing.EnterpriseInfoEntity;
import com.dl.service.EnterpriseInfoService;
import com.dl.utils.db.JdbcUtil;
import com.dl.utils.factory.DaoFactory;

public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

	@Override
	public int saveOrUpate(EnterpriseInfoEntity ai) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		EnterpriseInfoDao mid = DaoFactory.getEnterpriseInfoDao(jdbcutil);
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = mid.supdate(ai);
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return count;
	}

	@Override
	public List<EnterpriseInfoEntity> findAll() {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		EnterpriseInfoDao mid = DaoFactory.getEnterpriseInfoDao(jdbcutil);
		List<EnterpriseInfoEntity> list = null;
		try {
			conn.setAutoCommit(false);
			list = mid.findAll();
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return list;
	}

}
