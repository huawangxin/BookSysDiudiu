package com.youdian.model;

/**
 * 实体类：借书
 * 对应数据表：图书借阅表
 */
import java.sql.Date;

public class Borrow {
	private int id;
	private String bookISBN;
	private int operatorID;
	private String readerISBN;
	private int isback;
	private String borrowDate;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public int getOperatorID() {
		return operatorID;
	}
	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
	}
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}
	public int getIsback() {
		return isback;
	}
	public void setIsback(int isback) {
		this.isback = isback;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}