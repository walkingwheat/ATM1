package com.feicui.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UserTransfer {// 普通用户转账

	Scanner scanner = new Scanner(System.in);

	public void transfer(User user) {//

		while (true) {

			System.out.println("请输入对方账号");
			String input = scanner.next();// 输入要转入的账号

			File file = new File("text/userInfo.txt");
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				Map<String, Object> map = (Map<String, Object>) ois.readObject();// 将文本中读取的信息，存到map集合中去

				Set<String> setkey = map.keySet();// 将map集合的键组成set集合

				for (String key : setkey) {// 遍历set集合得到对应键的值
					Object obj = map.get(key);
					User otheruser = (User) obj;

					String otherAccount = otheruser.getUserAccount();// 定义另一个账户

					if (input.equals(otherAccount)) {

						while (true) {
							System.out.println("请输入转账金额");
							double amount = scanner.nextDouble();// 输入要转账的金额
							if (amount < 0 || amount % 100 != 0) {
								System.out.println("输入错误，请输入100的整数");
							} else if (amount > user.getDefaultBalance()) {
								System.out.println("余额不足");
							} else {
								System.out.println("转账成功");
								System.out.println("转账前余额:"+user.getDefaultBalance());
								user.setDefaultBalance(user.getDefaultBalance() - amount);
								otheruser.setDefaultBalance(user.getDefaultBalance() + amount);
								System.out.println("转账后余额:"+user.getDefaultBalance());

								String flow = "您给" + otherAccount + "转了" + amount + "元";
								record(user, flow);
								flow = user.getUserName() + "给您转了" + amount + "元";
								record(otheruser, flow);
								break;
							}

						}
						System.out.println("1.继续转账  2.返回上一级");
						input = scanner.next();
						if ("1".equals(input)) {
							transfer(user);
						} else if ("2".equals(input)) {
							UserMenu um = new UserMenu();
							um.userMenu(user);
						}

					} else {
						System.out.println("用户名输入错误");
					}

				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	public void record(User user, String flow) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		List<String> list = user.getRecode();// 得到list记录属性
		String time = sdf.format(date);

		list.add(flow + time);
		user.setRecode(list);

	}
}
