package com.youdian.model;

import java.sql.Date;
/**
 *ʵ���ࣺ�ṩ��
 *��Ӧ���ݱ�ͼ�鶩����Ϣ�� 
 */
public class Order {
	private String ISBN;
	private String dgdate;
	private int dgnumber;
	private String operator;
	private int checkAndAccept;
	private String zk;
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getDgdate() {
		return dgdate;
	}
	public void setDgdate(String dgdate) {
		this.dgdate = dgdate;
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
	public String getZk() {
		return zk;
	}
	public void setZk(String zk) {
		this.zk = zk;
	}
	
}