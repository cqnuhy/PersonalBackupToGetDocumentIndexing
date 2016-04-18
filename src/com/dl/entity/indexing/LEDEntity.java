package com.dl.entity.indexing;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：LEDEntity.java<br>
* 摘要：深市、沪市LED映射实体类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-11<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-11<br>
 */
public class LEDEntity {
	
	private String patentNumber;
	private String applyingNumber;
	private String license;
	/**
	 * 发明名称
	 */
	private String inventionName;
	/**
	 * 专利类型
	 */
	private String patentType;
	/**
	 * 专利名称
	 */
	private String patentName;
	
	private String _abstract;
	private String applicant;
	private String indexingConcepts;//受控词
	private String indexingWords;//非受控词
	public String getPatentNumber() {
		return patentNumber;
	}
	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
	}
	public String getApplyingNumber() {
		return applyingNumber;
	}
	public void setApplyingNumber(String applyingNumber) {
		this.applyingNumber = applyingNumber;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getInventionName() {
		return inventionName;
	}
	public void setInventionName(String inventionName) {
		this.inventionName = inventionName;
	}
	public String getPatentType() {
		return patentType;
	}
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String get_abstract() {
		return _abstract;
	}
	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
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
