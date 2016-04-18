package com.dl.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dl.entity.WordDetail;


/**
* 文件名称：WordDetailDao.java<br>
* 摘要：概念查询数据操作接口<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年6月29日<br>
 */
public interface WordDetailDao extends BaseDao<WordDetail,Serializable> {

	/**
	 * 查询出来Map格式数据
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> findMapByKey(String params) throws Exception;
	
	/**
	 * 保存或者更新一个概念查询实体
	 * @param wd
	 * @return
	 */
	public int supdate(WordDetail wd) throws SQLException ;

}
