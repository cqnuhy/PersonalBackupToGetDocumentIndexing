package com.dl.service.impl;

import java.util.List;

import com.dl.dao.LEDDao;
import com.dl.entity.indexing.LEDEntity;
import com.dl.service.LEDService;
import com.dl.utils.db.DBConnection;
import com.dl.utils.factory.DaoFactory;

public class LEDServiceImpl implements LEDService {

	@Override
	public List<LEDEntity> findAll(String queryType) {
		DBConnection conn = new DBConnection();
		LEDDao aid = DaoFactory.getLEDDao(conn.getConnection());
		List<LEDEntity> list = null;
		try {
			conn.setAutoCommit(false);
			list = aid.findAll(queryType);
			conn.setCommit();
		} catch (Exception e) {
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
	public int saveOrupate(List<LEDEntity> list,String saveType) {
		DBConnection conn = new DBConnection();
		LEDDao aid = DaoFactory.getLEDDao(conn.getConnection());
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = aid.saveALL(list,saveType);
			conn.setCommit();
		} catch (Exception e) {
			conn.setRollback();
			e.printStackTrace();
		} finally{
			aid.colseResultSet();
			aid.colsePreparedStatement();
			conn.close();
		}
		return count;
	}

	@Override
	public int saveOrupate(LEDEntity led, String saveType) {
		DBConnection conn = new DBConnection();
		LEDDao aid = DaoFactory.getLEDDao(conn.getConnection());
		int count = 0;
		try {
			conn.setAutoCommit(false);
			count = aid.save(led,saveType);
			conn.setCommit();
		} catch (Exception e) {
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
