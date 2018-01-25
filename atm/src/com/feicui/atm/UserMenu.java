package com.feicui.atm;

import java.util.Scanner;

public class UserMenu {

	Scanner scanner = new Scanner(System.in);

	public void userMenu(User user) {

		System.out.println("请输入要进行的操作(1.转账  2.取款  3.存款 4.查询 5.退出登录)");

		String input = scanner.next();

		if ("1".equals(input)) {
			UserTransfer ut = new UserTransfer();
			ut.transfer(user);
		} else if ("2".equals(input)) {
			UserDraw ud = new UserDraw();
			ud.drawMoney(user);
		} else if ("3".equals(input)) {
			UserDeposit udp = new UserDeposit();
			udp.depositMoney(user);
		} else if ("4".equals(input)) {
			UserQuery uq = new UserQuery();
			uq.queryInfo(user);

		} else if ("5".equals(input)) {
			LoginBusiness udp = new LoginBusiness();
			udp.showLogin();
		}
	}
}
