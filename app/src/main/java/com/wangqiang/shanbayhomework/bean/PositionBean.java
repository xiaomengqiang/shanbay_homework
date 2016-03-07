package com.wangqiang.shanbayhomework.bean;

public class PositionBean {
	private int start;
	private int end;

	public PositionBean(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	public PositionBean() {
		super();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
