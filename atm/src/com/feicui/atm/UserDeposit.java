package com.feicui.atm;

import java.util.Scanner;

public class UserDeposit {// 存款业务

	Scanner scanner = new Scanner(System.in);

	public void depositMoney(User user) {

		while (true) {

			System.out.println("请输入要存入的金额");
			double depositmoney = scanner.nextDouble();
			System.out.println("1.确认   2.重新存款");
			String input = scanner.next();

			if ("1".equals(input)) {
				// System.out.println("请存入100整数倍的金额");

				if (depositmoney % 100 == 0 && depositmoney > 0) {

					System.out.println("存款前余额是:" + user.getDefaultBalance());
					user.setDefaultBalance(user.getDefaultBalance() + depositmoney);
					System.out.println("存款后余额是:" + user.getDefaultBalance());

					String flow = "你存入了" + depositmoney + "元";
					UserTransfer ut = new UserTransfer();
					ut.record(user, flow);

					while (true) {
						System.out.println("1.继续存款   2.返回菜单");
						String str = scanner.next();

						if ("1".equals(str)) {
							this.depositMoney(user);
						} else if ("2".equals(str)) {
							UserMenu um = new UserMenu();
							um.userMenu(user);
						}
					}

				} else {
					System.out.println("请存入100整数倍的金额");
				}
			} else if("2".equals(input)){
				this.depositMoney(user);
				
			}
		}
	}
}
