package com.dl.service;

import java.util.List;
import java.util.Map;

import com.dl.entity.WordDetail;


/**
 * 
* 文件名称：EmployeeService.java<br>
* 摘要：概念查询业务操作接口<br>
* -------------------------------------------------------<br>
* 作者：胡毅<br>
* 完成日期：2015年6月30日<br>
 */
public interface WordDetailService {

	public List<WordDetail> findAll();
	
	public List<Map<String, Object>> findMap(String param);
	
	public int saveOrUpate(WordDetail wd);
}
