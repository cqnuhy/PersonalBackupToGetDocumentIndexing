package com.dl.dao;

import java.io.Serializable;
import com.dl.entity.AutoIndexing;

/**
* 文件名称：WordDetailDao.java<br>
* 摘要：摘要标引数据操作接口<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年6月29日<br>
 */
public interface AutoIndexingDao extends BaseDao<AutoIndexing, Serializable>{

	/**
	 * 保存或者更新一个摘要标引实体
	 * @param wd
	 * @return
	 */
	public int supdate(AutoIndexing wd);
}
