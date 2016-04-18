package com.dl.entity.indexing;

import java.io.Serializable;

/**
* Copyright (c) 2016,HUYI<br>
* All rights reserved.<br>
* 
* 文件名称：EnterpriseInfoEntity.java<br>
* 摘要：企业基本信息映射实体<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：HUYI<br>
* 完成日期：2016-3-9<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：HUYI<br>
* 完成日期：2016-3-9<br>
 */
public class EnterpriseInfoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String stockCode;
	private String R_DProjectKeypoint;
	private String R_DProjectKeypointIC; // 研发项目重点受控词
	private String R_DProjectKeypointIW; // 研发项目重点非受控词
	private String coreTechnology;
	private String coreTechnologyIC;// 关键技术受控词
	private String coreTechnologyIW;// 关键技术非受控词
	private String coreCompetitive ;// 核心竞争力
	private String coreCompetitiveIC;
	private String coreCompetitiveIW ;
	private String coreProduct;// 主要产品
	private String coreProductIC;
	private String coreProductIW;
	private String businessScope; // 经营范围
	private String businessScopeIC;
	private String businessScopeIW;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getR_DProjectKeypoint() {
		return R_DProjectKeypoint;
	}
	public void setR_DProjectKeypoint(String r_DProjectKeypoint) {
		R_DProjectKeypoint = r_DProjectKeypoint;
	}
	public String getR_DProjectKeypointIC() {
		return R_DProjectKeypointIC;
	}
	public void setR_DProjectKeypointIC(String r_DProjectKeypointIC) {
		R_DProjectKeypointIC = r_DProjectKeypointIC;
	}
	public String getR_DProjectKeypointIW() {
		return R_DProjectKeypointIW;
	}
	public void setR_DProjectKeypointIW(String r_DProjectKeypointIW) {
		R_DProjectKeypointIW = r_DProjectKeypointIW;
	}
	public String getCoreTechnology() {
		return coreTechnology;
	}
	public void setCoreTechnology(String coreTechnology) {
		this.coreTechnology = coreTechnology;
	}
	public String getCoreTechnologyIC() {
		return coreTechnologyIC;
	}
	public void setCoreTechnologyIC(String coreTechnologyIC) {
		this.coreTechnologyIC = coreTechnologyIC;
	}
	public String getCoreTechnologyIW() {
		return coreTechnologyIW;
	}
	public void setCoreTechnologyIW(String coreTechnologyIW) {
		this.coreTechnologyIW = coreTechnologyIW;
	}
	public String getCoreCompetitive() {
		return coreCompetitive;
	}
	public void setCoreCompetitive(String coreCompetitive) {
		this.coreCompetitive = coreCompetitive;
	}
	public String getCoreCompetitiveIC() {
		return coreCompetitiveIC;
	}
	public void setCoreCompetitiveIC(String coreCompetitiveIC) {
		this.coreCompetitiveIC = coreCompetitiveIC;
	}
	public String getCoreCompetitiveIW() {
		return coreCompetitiveIW;
	}
	public void setCoreCompetitiveIW(String coreCompetitiveIW) {
		this.coreCompetitiveIW = coreCompetitiveIW;
	}
	public String getCoreProduct() {
		return coreProduct;
	}
	public void setCoreProduct(String coreProduct) {
		this.coreProduct = coreProduct;
	}
	public String getCoreProductIC() {
		return coreProductIC;
	}
	public void setCoreProductIC(String coreProductIC) {
		this.coreProductIC = coreProductIC;
	}
	public String getCoreProductIW() {
		return coreProductIW;
	}
	public void setCoreProductIW(String coreProductIW) {
		this.coreProductIW = coreProductIW;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getBusinessScopeIC() {
		return businessScopeIC;
	}
	public void setBusinessScopeIC(String businessScopeIC) {
		this.businessScopeIC = businessScopeIC;
	}
	public String getBusinessScopeIW() {
		return businessScopeIW;
	}
	public void setBusinessScopeIW(String businessScopeIW) {
		this.businessScopeIW = businessScopeIW;
	}
	
}
