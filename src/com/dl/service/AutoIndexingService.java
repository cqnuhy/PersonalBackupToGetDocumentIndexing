package com.dl.service;

import java.util.List;
import java.util.Map;

import com.dl.entity.AutoIndexing;


/**
 * 
* 文件名称：EmployeeService.java<br>
* 摘要：摘要标引业务操作接口<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年6月30日<br>
 */
public interface AutoIndexingService {
	
	public List<AutoIndexing> findAll();

	public List<Map<String, Object>> findOne(String id);

	public int saveOrUpate(AutoIndexing wd);
}
