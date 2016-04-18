package com.dl.service.impl;

import java.sql.Connection;
import java.util.List;

import com.dl.dao.LEDDao;
import com.dl.entity.indexing.LEDEntity;
import com.dl.service.LEDService;
import com.dl.utils.db.JdbcUtil;
import com.dl.utils.factory.DaoFactory;

public class LEDServiceImpl implements LEDService {

	@Override
	public List<LEDEntity> findAll(String queryType) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		LEDDao aid = DaoFactory.getLEDDao(jdbcutil);
		List<LEDEntity> list = null;
		try {
			conn.setAutoCommit(false);
			list = aid.findAll(queryType);
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return list;
	}

	@Override
	public int saveOrupate(List<LEDEntity> list,String saveType) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		LEDDao aid = DaoFactory.getLEDDao(jdbcutil);
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = aid.saveALL(list,saveType);
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return count;
	}

	@Override
	public int saveOrupate(LEDEntity led, String saveType) {
		JdbcUtil jdbcutil = new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		LEDDao aid = DaoFactory.getLEDDao(jdbcutil);
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = aid.save(led,saveType);
			conn.commit();
		} catch (Exception e) {
			jdbcutil.rollback(conn);
		} finally{
			jdbcutil.close(conn);
		}
		return count;
	}

}
