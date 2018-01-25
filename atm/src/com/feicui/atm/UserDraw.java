package com.feicui.atm;

import java.util.Scanner;

public class UserDraw {// 取款业务

	Scanner scanner = new Scanner(System.in);

	public void drawMoney(User user) {

		while (true) {

			System.out.println("请输入取款金额(100整数倍)");
			double drawmoney = scanner.nextDouble();

			System.out.println("1.确认  2.返回");
			String str = scanner.next();
			if ("1".equals(str)) {

				if (drawmoney % 100 == 0 && drawmoney > 0) {

					System.out.println("取款前金额:" + user.getDefaultBalance());
					user.setDefaultBalance(user.getDefaultBalance() - drawmoney);
					System.out.println("取款后的金额:" + user.getDefaultBalance());
					
					String flow = "您取出了" + drawmoney + "元";
					UserTransfer ut = new UserTransfer();
					ut.record(user, flow);
					
					while(true) {
						
						System.out.println("1.继续取款   2.返回菜单");
						String input = scanner.next();
						if ("1".equals(input)) {
							this.drawMoney(user);
						} else if("2".equals(input)) {
							UserMenu um = new UserMenu();
							um.userMenu(user);
						}
					}
					
				} else if (drawmoney > user.getDefaultBalance()) {
					System.out.println("余额不足，请重新输入");
					this.drawMoney(user);

				} else {
					System.out.println("输入错误，请重新输入大于0且是100整数倍的金额");
					this.drawMoney(user);
				}
			} else if ("2".equals(str)) {
				this.drawMoney(user);
			}

		}

	}
}
