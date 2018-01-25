package com.feicui.atm;

public class UserQuery {
	
	//public void queryInfo(User user) {
		
		//System.out.println("账号："+user.getUserAccount());
//		System.out.println("密码："+user.getUserPassword());
//		System.out.println("流水："+user.getUserAccount());
//		System.out.println("账号："+user.getUserAccount());
		
		public String queryInfo(User user) {
			return "User [账号=" + user.getUserAccount() + ", 密码=" + user.getUserPassword() + ", 用户名=" + user.getUserName()
					+ ", 性别=" + user.getSex() + ", 身份证号=" + user.getID() + ", 学历=" + user.getEdu() + ", 余额=" + user.getDefaultBalance() + ", 地址="
					+ user.getAddress() + "]";
	}

}
