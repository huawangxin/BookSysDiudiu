package com.youdian.model;

import java.sql.Date;
/**
 * 实体类：读者信息
 *对应数据表：读者信息表
 */
public class Reader {
	private String readername;
	private String gender;
	private int age;
	private String job;
	private String identityCard;
	private String vipDate;
	private String tel;
	private int maxborrow;
	private String keepMoney;
	private String bztime;
	private String readercode;
	public String getReadername() {
		return readername;
	}
	public void setReadername(String readername) {
		this.readername = readername;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getVipDate() {
		return vipDate;
	}
	public void setVipDate(String vipDate) {
		this.vipDate = vipDate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getMaxborrow() {
		return maxborrow;
	}
	public void setMaxborrow(int maxborrow) {
		this.maxborrow = maxborrow;
	}
	public String getKeepMoney() {
		return keepMoney;
	}
	public void setKeepMoney(String keepMoney) {
		this.keepMoney = keepMoney;
	}
	public String getBztime() {
		return bztime;
	}
	public void setBztime(String bztime) {
		this.bztime = bztime;
	}
	public String getReadercode() {
		return readercode;
	}
	public void setReadercode(String readercode) {
		this.readercode = readercode;
	}
	
	
}	