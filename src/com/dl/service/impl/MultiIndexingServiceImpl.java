package com.dl.service.impl;

import java.util.List;
import java.util.Map;

import com.dl.dao.MultiIndexingDao;
import com.dl.entity.indexing.MultiIndexingEntity;
import com.dl.service.MultiIndexingService;
import com.dl.utils.db.DBConnection;
import com.dl.utils.factory.DaoFactory;

public class MultiIndexingServiceImpl implements MultiIndexingService {

	@Override
	public List<MultiIndexingEntity> findJointEnterpriseData(Map<String,String> pars) {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(conn.getConnection());
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			mid.colseResultSet();
			mid.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public int saveOrUpate(MultiIndexingEntity mie,int menu) {
		DBConnection conn = new DBConnection();
		MultiIndexingDao aid= DaoFactory.getMultiIndexingDao(conn.getConnection());
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = aid.supdate(mie,menu);
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

	@Override
	public List<MultiIndexingEntity> findParentCompanyData(
			Map<String, String> pars) {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(conn.getConnection());
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			mid.colseResultSet();
			mid.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public List<MultiIndexingEntity> findKeypointProjectData(
			Map<String, String> pars) {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(conn.getConnection());
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			mid.colseResultSet();
			mid.colsePreparedStatement();
			conn.close();
		}
		return list;
	}

	@Override
	public List<MultiIndexingEntity> findSubCompanyData(Map<String, String> pars) {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(conn.getConnection());
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
			//提交事务
			conn.setCommit();
		} catch (Exception e) {
			//回滚事务
			conn.setRollback();
			e.printStackTrace();
		} finally{
			mid.colseResultSet();
			mid.colsePreparedStatement();
			conn.close();
		}
		return list;	
	}

}
