package com.dl.service;

import java.util.List;
import java.util.Map;

import com.dl.entity.indexing.MultiIndexingEntity;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：MultiIndexingService.java<br>
* 摘要：多张数据表查询用service<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-10<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-10<br>
 */
public interface MultiIndexingService {

	/**
	 * 查询联营企业数据
	 * @date 2016-3-10 上午1:17:57
	 * @author HUYI 
	 * @param pars
	 * @return
	 */
	public List<MultiIndexingEntity> findJointEnterpriseData(Map<String,String> pars);

	public int saveOrUpate(MultiIndexingEntity ai,int menu);

	/**
	 * 查询母公司数据
	 * @date 2016-3-10 下午1:07:47
	 * @author HUYI 
	 * @param pars
	 * @return
	 */
	public List<MultiIndexingEntity> findParentCompanyData(
			Map<String, String> pars);

	/**
	 * 查询重点项目数据
	 * @date 2016-3-10 下午1:09:41
	 * @author HUYI 
	 * @param pars
	 * @return
	 */
	public List<MultiIndexingEntity> findKeypointProjectData(
			Map<String, String> pars);

	/**
	 * 查询子公司数据
	 * @date 2016-3-10 下午1:10:07
	 * @author HUYI 
	 * @param pars
	 * @return
	 */
	public List<MultiIndexingEntity> findSubCompanyData(Map<String, String> pars);

}
