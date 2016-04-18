package com.dl.service.impl;

import java.util.List;

import com.dl.dao.EnterpriseInfoDao;
import com.dl.entity.indexing.EnterpriseInfoEntity;
import com.dl.service.EnterpriseInfoService;
import com.dl.utils.db.DBConnection;
import com.dl.utils.factory.DaoFactory;

public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

	@Override
	public int saveOrUpate(EnterpriseInfoEntity ai) {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		EnterpriseInfoDao mid = DaoFactory.getEnterpriseInfoDao(conn.getConnection());
		int count = 0;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			count = mid.supdate(ai);
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
		return count;
	}

	@Override
	public List<EnterpriseInfoEntity> findAll() {
		DBConnection conn = new DBConnection();
		// 通过工厂获取Dao,在业务层与数据层解耦合
		EnterpriseInfoDao mid = DaoFactory.getEnterpriseInfoDao(conn.getConnection());
		List<EnterpriseInfoEntity> list = null;
		try {
			//添加手动事务管理
			conn.setAutoCommit(false);
			//执行业务
			list = mid.findAll();
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
