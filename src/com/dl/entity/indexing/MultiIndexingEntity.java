package com.dl.entity.indexing;

import java.io.Serializable;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：MultiIndexingEntity.java<br>
* 摘要：数据库映射实体，用于联营企业、母公司、子公司、重点项目数据表的复合映射类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-9<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-9<br>
 */
public class MultiIndexingEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;// 企业名称
	private String business;// 业务
	private String indexingConcepts;//受控词
	private String indexingWords;//非受控词
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getIndexingConcepts() {
		return indexingConcepts;
	}
	public void setIndexingConcepts(String indexingConcepts) {
		this.indexingConcepts = indexingConcepts;
	}
	public String getIndexingWords() {
		return indexingWords;
	}
	public void setIndexingWords(String indexingWords) {
		this.indexingWords = indexingWords;
	}
}
