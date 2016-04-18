package com.dl.entity;

// 摘要标引数据表映射实体类,每个属性映射一个数据库字段
public class AutoIndexing {

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String title;
	private String _abstract;
	private Integer result;
	private String message;
	private String indexingConcepts;
	private String indexingWords;
	private String clcNumbers;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String get_abstract() {
		return _abstract;
	}
	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getClcNumbers() {
		return clcNumbers;
	}
	public void setClcNumbers(String clcNumbers) {
		this.clcNumbers = clcNumbers;
	}
}
