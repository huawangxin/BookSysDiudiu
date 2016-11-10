package com.youdian.model;
/**
 * 实体类：还书 
 * 对应数据表：图书借阅信息表
 */
public class Back {
	private int id;
	private String bookISBN;
	private int operatorID ;
	private String readerID;
	private int isback;
	private String borrowDate;
	private String backDate;
	private String BookName;
	private String ReaderName;
	private String ReaderISBN;
	private int TypeId;
	
	public int getTypeId() {
		return TypeId;
	}
	public void setTypeId(int typeId) {
		TypeId = typeId;
	}
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
	public String getReaderID() {
		return readerID;
	}
	public void setReaderID(String readerID) {
		this.readerID = readerID;
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
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getReaderName() {
		return ReaderName;
	}
	public void setReaderName(String readerName) {
		ReaderName = readerName;
	}
	public String getReaderISBN() {
		return ReaderISBN;
	}
	public void setReaderISBN(String readerISBN) {
		ReaderISBN = readerISBN;
	}
	
}
