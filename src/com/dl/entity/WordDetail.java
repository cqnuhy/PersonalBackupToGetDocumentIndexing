package com.dl.entity;

// 概念查询数据表映射实体类,每个属性映射一个数据库字段
public class WordDetail {

	private String id;
	private String word ; // 查询关键词
	private String wordF; // 上位词
	private String wordC; // 下位词
	private String wordD; // 同义词
	private String wordEnglish ; //英文翻译
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getWordF() {
		return wordF;
	}
	public void setWordF(String wordF) {
		this.wordF = wordF;
	}
	public String getWordC() {
		return wordC;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWordC(String wordC) {
		this.wordC = wordC;
	}
	public String getWordD() {
		return wordD;
	}
	public void setWordD(String wordD) {
		this.wordD = wordD;
	}
	public String getWordEnglish() {
		return wordEnglish;
	}
	public void setWordEnglish(String wordEnglish) {
		this.wordEnglish = wordEnglish;
	}

}
