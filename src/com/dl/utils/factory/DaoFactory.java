package com.dl.utils.factory;

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
import com.dl.utils.db.JdbcUtil;



/**
 * 文件名称：DaoFactory.java<br>
 * 摘要：Dao工厂类，防止业务层new数据实现类出现耦合<br>
 * -------------------------------------------------------<br>
 * 作者：胡毅<br>
 * 完成日期：2015年6月29日<br>
 */
public class DaoFactory {
	
	public static WordDetailDao getWordDetailDao(JdbcUtil jdbcutil) {
		return new WordDetailDaoImpl(jdbcutil);
	}

	public static AutoIndexingDao getAutoIndexingDao(JdbcUtil jdbcutil) {
		return new AutoIndexingDaoImpl(jdbcutil);
	}

	public static MultiIndexingDao getMultiIndexingDao(JdbcUtil jdbcutil) {
		return new MultiIndexingDaoImpl(jdbcutil);
	}

	public static EnterpriseInfoDao getEnterpriseInfoDao(JdbcUtil jdbcutil) {
		return new EnterpriseInfoDaoImpl(jdbcutil);
	}

	public static LEDDao getLEDDao(JdbcUtil jdbcutil) {
		return new LEDDaoImpl(jdbcutil);
	}

}
