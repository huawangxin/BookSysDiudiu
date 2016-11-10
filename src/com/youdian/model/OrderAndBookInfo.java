package com.youdian.model;

import java.util.Date;

/**
 * 实体类：订购和图书信息 
 * 对应数据表：订购和图书信息表
 */
public class OrderAndBookInfo {
	private String ISBN;
	private int dgnumber;
	private String operator;
	private int checkAndAccept;
	private int typeId;
	private String bookName;
	private String writer;
	private String translator;
	private String publisher;
	private String publish_date;
	private String price;
	
	private String Orderdate;
	private String Discount;
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	
	
	public int getDgnumber() {
		return dgnumber;
	}

	public void setDgnumber(int dgnumber) {
		this.dgnumber = dgnumber;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getCheckAndAccept() {
		return checkAndAccept;
	}

	public void setCheckAndAccept(int checkAndAccept) {
		this.checkAndAccept = checkAndAccept;
	}


	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publishDate) {
		publish_date = publishDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOrderdate() {
		return Orderdate;
	}

	public void setOrderdate(String orderdate) {
		Orderdate = orderdate;
	}
	
	
}