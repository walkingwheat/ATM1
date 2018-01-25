package com.feicui.atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 行走的麦子
 *
 */
public class User implements Serializable{//序列化要实现serializable接口
	private String userAccount;
	private String userPassword;
	private String userName;
	private String sex;
	private String ID;
	private String edu;
	private double defaultBalance;
	private String address;
	private List<String> recode = new ArrayList<>();
	
	
	public List<String> getRecode() {
		return recode;
	}

	public void setRecode(List<String> recode) {
		this.recode = recode;
	}

	public User() {
		
	} 

	public User(String userAccount) {//带有账号信息的构造器，在修改账户信息的时候使用
		super();
		this.userAccount = userAccount;
	}

	//带有账号和身份证号对的构造方法执行销户的时候作为键使用
	public User(String userAccount, String iD) {
		super();
		this.userAccount = userAccount;
		ID = iD;
	}

	public User(String userAccount, String userPassword,
			String userName, String sex, String iD, String edu,
			double defaultBalance, String address) {
		super();
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userName = userName;
		this.sex = sex;
		ID = iD;
		this.edu = edu;
		this.defaultBalance = defaultBalance;
		this.address = address;
	}


	public String getUserAccount() {
		return userAccount;
	}


	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getEdu() {
		return edu;
	}


	public void setEdu(String edu) {
		this.edu = edu;
	}


	public double getDefaultBalance() {
		return defaultBalance;
	}


	public void setDefaultBalance(double defaultBalance) {
		this.defaultBalance = defaultBalance;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userAccount=" + userAccount + ", userPassword=" + userPassword + ", userName=" + userName
				+ ", sex=" + sex + ", ID=" + ID + ", edu=" + edu + ", defaultBalance=" + defaultBalance + ", address="
				+ address + "]";
	}
	
	@Override // 重写equals方法
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this) {// obj 与当前对象地址相同
			return true;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		if (((User) obj).userAccount.equals(userAccount)) {// 账户相同
			return true;
		}

		if (((User) obj).ID.equals(ID)) {// 身份证号相同
			return true;
		}

		return false;
	}
	
	
}
