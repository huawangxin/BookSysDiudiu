package com.youdian.model;
/**
 * 实体类：图书分类
 * 对应数据表：图书分类信息表
 */
public class BookType {
	private int id;
	private String type;
	private int days;
	private String fk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	
}
