package com.dl.utils.factory;

import java.sql.Connection;

import com.dl.dao.AutoIndexingDao;
import com.dl.dao.EnterpriseInfoDao;
import com.dl.dao.LEDDao;
import com.dl.dao.MultiIndexingDao;
import com.dl.dao.WordDetailDao;
import com.dl.dao.impl.AutoIndexingDaoImpl;
import com.dl.dao.impl.EnterpriseInfoDaoImpl;
import com.dl.dao.impl.LEDDaoImpl;
import com.dl.dao.impl.MultiIndexingDaoImpl;
import com.dl.dao.impl.WordDetailDaoImpl;



/**
 * 文件名称：DaoFactory.java<br>
 * 摘要：Dao工厂类，防止业务层new数据实现类出现耦合<br>
 * -------------------------------------------------------<br>
 * 作者：胡毅<br>
 * 完成日期：2015年6月29日<br>
 */
public class DaoFactory {
	public static WordDetailDao getWordDetailDao(Connection conn) {
		return new WordDetailDaoImpl(conn);
	}

	public static AutoIndexingDao getAutoIndexingDao(Connection conn) {
		return new AutoIndexingDaoImpl(conn);
	}

	public static MultiIndexingDao getMultiIndexingDao(Connection conn) {
		return new MultiIndexingDaoImpl(conn);
	}

	public static EnterpriseInfoDao getEnterpriseInfoDao(Connection conn) {
		return new EnterpriseInfoDaoImpl(conn);
	}

	public static LEDDao getLEDDao(Connection conn) {
		return new LEDDaoImpl(conn);
	}

}
