package com.dl.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 文件名称：BaseDao.java<br>
 * 摘要：基本Dao类<br>
 * -------------------------------------------------------<br>
 * 作者：胡毅<br>
 * 完成日期：2015年6月29日<br>
 * 
 * @param <T>
 */
public interface BaseDao<T,ID extends Serializable> {

	/**
	 * 查询集合数据
	 * @date 2015年6月30日 上午11:00:30
	 * @author 胡毅
	 * @return
	 * @throws Exception
	 */
	public List<T> findAll() throws Exception;

	/**
	 * 查询单条数据
	 * @date 2015年6月30日 上午11:00:51
	 * @author 胡毅
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<T> findOne(String id) throws Exception;
	
	/**
	 * 关闭Statement和ResultSet
	 * @date 2015年6月30日 上午11:01:05
	 * @author 胡毅
	 */
	public void colsePreparedStatement();
	public void colseResultSet();
}
