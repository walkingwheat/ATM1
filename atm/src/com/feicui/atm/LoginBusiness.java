package com.feicui.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class LoginBusiness implements Serializable {
	private String manageAccount = "1111";
	private String managePassWord = "0000";
	private String manageName = "翡翠侠";

	Scanner scanner = new Scanner(System.in);

	public void showLogin() {
		File file = new File("text/login.txt");

		while (true) {

			FileReader fr = null;
			try {
				fr = new FileReader(file);
				char[] charArr = new char[50];
				int count = 0;
				while ((count = fr.read(charArr)) != -1) {
					System.out.println(charArr);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			String input = scanner.next();
			if (input.equals("1")) {
				manageLogin();
			} else if (input.equals("2")) {
				userLogin();
			} else {
				System.out.println("输入错误，请重新输入");
			}
		}
	}

	public void manageLogin() {
		File file = new File("text/message.properties");
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
			String value = prop.getProperty("T001");
			System.out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String input = scanner.next();
		while (true) {

			if (input.equals(manageAccount)) {
				String value = prop.getProperty("T002");// 显示输入密码
				System.out.println(value);
				input = scanner.next();

				if (input.equals(managePassWord)) {
					value = prop.getProperty("M001");// 显示登录成功
					System.out.println(value);
					break;
				} else {
					String value1 = prop.getProperty("E002");// 显示密码输入错误
					// value = prop.getProperty("E003");
					System.out.println(value1);
					manageLogin();
				}
			} else {
				String value = prop.getProperty("E001");// 显示密码输入错误
				System.out.println(value);
				manageLogin();
			}
		}
		ManageMenuBusiness mb = new ManageMenuBusiness();
		mb.menu();
	}

	public void userLogin() {

		while (true) {

			File file = new File("text/message.properties");
			Properties prop = new Properties();

			if (file.length() == 0) {
				System.out.println("普通用户不存在");
				showLogin();
			} else {

				ObjectInputStream ois;
				try {
					prop.load(new FileInputStream(file));
					String value = prop.getProperty("T001");
					System.out.println(value);

					ois = new ObjectInputStream(new FileInputStream("text/userInfo.txt"));
					Map<String, User> map = (Map<String, User>) ois.readObject();

					ois.close();

					System.out.println(">>>>>>>>>>>");
					if (map == null || map.isEmpty()) {
						System.out.println("普通用户不存在，请注册后再登录");
						showLogin();

					} else {
						System.out.println("请输入账号(支持账号和身份证号登录)");
					}
					Set<String> keyset = map.keySet();
					String input = scanner.next();

					for (String key : keyset) {
						if (key.startsWith(input) || key.endsWith(input)) {

							Object obj = map.get(key);// 将获取的对应键的值赋给obj

							User user = (User) obj;// 再将值赋给user
							String userPassword = user.getUserPassword();

							// System.out.println("用户密码"+userPassword);
							while (true) {
								System.out.println("请输入密码(至少8位,数字和字母必须同时又,至少一个大写字母)");

								String password = scanner.next();
								if (userPassword.equals(password)) {
									System.out.println("登录成功");
									UserMenu um = new UserMenu();
									um.userMenu(user);

								} else {
									System.out.println("密码输入错误，请重新输入");
								}
							}
						} else {
							System.out.println("用户不存在");
						}
					}

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();

				}
			}

		}

	}

}
