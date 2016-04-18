package com.dl.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dl.dao.MultiIndexingDao;
import com.dl.entity.indexing.MultiIndexingEntity;
import com.dl.service.MultiIndexingService;
import com.dl.utils.db.JdbcUtil;
import com.dl.utils.factory.DaoFactory;

public class MultiIndexingServiceImpl implements MultiIndexingService {

	@Override
	public List<MultiIndexingEntity> findJointEnterpriseData(Map<String,String> pars) {
		JdbcUtil jdbcutil= new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(jdbcutil);
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
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
	public int saveOrUpate(MultiIndexingEntity mie,int menu) {
		JdbcUtil jdbcutil= new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		MultiIndexingDao aid= DaoFactory.getMultiIndexingDao(jdbcutil);
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = aid.supdate(mie,menu);
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

	@Override
	public List<MultiIndexingEntity> findParentCompanyData(
			Map<String, String> pars) {
		JdbcUtil jdbcutil= new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(jdbcutil);
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
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
	public List<MultiIndexingEntity> findKeypointProjectData(
			Map<String, String> pars) {
		JdbcUtil jdbcutil= new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(jdbcutil);
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
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
	public List<MultiIndexingEntity> findSubCompanyData(Map<String, String> pars) {
		JdbcUtil jdbcutil= new JdbcUtil();
		Connection conn = jdbcutil.getConnection();
		MultiIndexingDao mid = DaoFactory.getMultiIndexingDao(jdbcutil);
		List<MultiIndexingEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAllByMenu(Integer.valueOf(pars.get("menu")));
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

}
