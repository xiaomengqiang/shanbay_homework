package com.wangqiang.shanbayhomework.bean;

public class WordBean {
    private String word;
    private int level;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public WordBean(String word, int level) {
		super();
		this.word = word;
		this.level = level;
	}
	public WordBean() {
		super();
	}
}
