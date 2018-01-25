package com.feicui.atm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AddAccountBusiness implements Serializable {
	Scanner scanner = new Scanner(System.in);
	int valueLength = 0;
	private String sex;
	private String time;
	private String userAccount;
	private String userName;
	private String edu;
	private double defaultBalance;
	private User user;
	private String ID;
	private String address;
	private String userKey;

	List<User> list = new ArrayList<>();
	Map<String, User> map = new HashMap<String, User>();

	// ********************************开户*************************************
	public void addAccount() {

		System.out.println("请输入初始密码，不少于8位");

		String userPassword = scanner.next();
		label1: while (true) {
			// 输入初始密码
			// 根据正则表达式判断用户密码
			if (userPassword.matches("[0-9a-zA-Z]{7,}[A-Z]{1,}|[A-Z]{1,}[0-9a-zA-Z]{7,}")) {
				System.out.println("密码设置成功");
				System.out.println("密码" + userPassword);

				while (true) {

					userNameLength();// 输入要创建的用户名
					if (valueLength <= 10) {// 姓名长度小于10
						System.out.println("用户创建成功");
						while (true) {
							System.out.println("请输入性别,确认后不可更改");
							System.out.println("1.男  2.女");
							String sex = scanner.next();// 输入性别
							if (sex.equals("1")) {
								this.sex = "01";
								System.out.println("先生，你好");
								break;
							} else if (sex.equals("2")) {
								this.sex = "02";
								System.out.println("女士，你好");
								break;
							} else {
								System.out.println("输入错误，请重新输入");
								continue;
							}
						}

						while (true) {

							System.out.println("请输入18位有效身份证号，确认后不可修改");
							ID = scanner.next();// 输入身份证号
							// 判断身份证号是否是18位
							if (ID.matches("[1-9]{1}[0-9]{16}([0-9]|X)")) {
								System.out.println("身份证输入完毕");
								System.out.println("身份证号" + ID);
								// 输入学历
								System.out.println("请输入你的学历(1.小学2.中学3.大学4.其他)");

								this.edu = scanner.next();
								if (edu.equals("1")) {
									edu = "小学";
									System.out.println("你输入的学历是小学");

								} else if (edu.equals("2")) {
									edu = "中学";
									System.out.println("你输入的学历是中学");

								} else if (edu.equals("3")) {
									edu = "大学";
									System.out.println("你输入的学历是大学");

								} else if (edu.equals("4")) {
									edu = "其他";
									System.out.println("你输入的学历是其他");

								}

								System.out.println("默认余额为0");
								defaultBalance = 0;// 设置默认余额为0

								System.out.println("请输入联系地址:不超过25个字");
								this.address = scanner.next();// 输入用户地址
								System.out.println("地址" + address);

								while (true) {

									if (valueLength <= 50) {// 地址长度小于50
										System.out.println("地址输入成功");

										getAccount();
										userAccount = "37" + sex + time;
										System.out.println(userAccount);
										break label1;// 此标签结束整个while循环
									} else {
										System.out.println("地址输入出错，请重新输入");
									}

								}
							} else {
								System.out.println("你输入的身份证号不合法，请重新输入");
							}
						}

					} else {
						System.out.println("用户格式输入错误，请重新输入");
					}
				}

			} else {
				System.out.println("密码输入错误请重新输入");
				break;
			}
		}

		User user = new User(userAccount, userPassword, userName, sex, ID, edu, defaultBalance, address);
		System.out.println(user);
		System.out.println("开户成功");
		String key = userAccount + ID;
		map.put(key, user);

		System.out.println("请选择要进行的操作:(1.返回主菜单   2.重新注册 3.退出)");
		String input = scanner.next();

		if (input.equals("1")) {
			write(map);
			ManageMenuBusiness mmb = new ManageMenuBusiness();
			mmb.menu();
		} else if (input.equals("2")) {
			addAccount();
		} else if (input.equals("3")) {
			System.exit(0);
		}
	}

	public int userNameLength() {

		System.out.println("请输入姓名");
		userName = scanner.next();
		// 判断中文姓名长度
		// int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < userName.length(); i++) {
			/* 获取一个字符 */
			String temp = userName.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	public int userAddressLength() {

		System.out.println("请输入账户名");
		String address = scanner.next();
		// 判断中文姓名长度
		// int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < address.length(); i++) {
			/* 获取一个字符 */
			String temp = address.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;

	}

	public void getAccount() {
		Date date = new Date();
		SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMddhhmmssSSS");// 获取系统当前时间用作账号
		time = sfd.format(date);
		String userAccount = "37" + sex + time;

	}

	File file = new File("text/userInfo.txt");

	public void write(Map<String, User> map) {

		if (file == null || file.length() == 0 || file.equals("")) {
			Map<String, User> newmap = new HashMap<>();
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(file));
				newmap.putAll(map);
				oos.writeObject(newmap);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				Map<String, User> newmap = (Map<String, User>) ois.readObject();
				newmap.putAll(map);
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(newmap);
				oos.close();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// ********************************销户*****************************************************
	public void deleteAccount() {

		System.out.println("请输入要删除的账号");

		String deleteAccount = scanner.next();

		System.out.println("请输入要删除账号用户的身份证号");

		String deletePassword = scanner.next();

		String str = deleteAccount + deletePassword;

		File file = new File("text/userInfo.txt");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, User> map = new HashMap<>();
		try {
			map = (HashMap<String, User>) ois.readObject();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (map.containsKey(str)) {
			// User user = map.get(str);
			while (true) {
				System.out.println("确认要删除账号吗?(1.确定 2.取消)");
				String input = scanner.next();

				if (input.equals("1")) {
					map.remove(str);
					System.out.println("删除成功！");
					
					ObjectOutputStream oos;
					try {
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(map);
						oos.close();
						break;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		} else {
			System.out.println("账号不存在，请重新输入");
			deleteAccount();
		}

		System.out.println("请选择要进行的操作:(1.返回主菜单   2.退出)");
		String input = scanner.next();

		if (input.equals("1")) {
			ManageMenuBusiness mmb = new ManageMenuBusiness();
			mmb.menu();
		} else {

		}
	}

	// ********************************查询信息**************************************************
	public void showAccount() {

		File file = new File("text/userInfo.txt");

		if (file.equals(null) || file.length() == 0) {
			Map<String, Object> map = new HashMap<>();
			System.out.println("无用户");
			addAccount();

		} else {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				Map<String, User> map = (HashMap<String, User>) ois.readObject();

				Set<String> keySet = map.keySet();
				System.out.println(keySet.size());
				Iterator<String> it = keySet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					User user = map.get(key);
					System.out.println("key" + key + "    value" + user);
				}
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("请选择要进行的操作:(1.返回主菜单   2.退出)");
		String input = scanner.next();

		if (input.equals("1")) {
			ManageMenuBusiness mmb = new ManageMenuBusiness();
			mmb.menu();
		} else {

		}
	}

	// ***********************************修改账户信息***********************************************************************8
	public void changeAccount() {
		File file = new File("text/userInfo.txt");
		if (file == null || file.length() == 0) {
			System.out.println("用户不存在");
		} else {
			while (true) {

				System.out.println("请输入你要修改的账号");
				String input = scanner.next();
				// change(user);

				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(new FileInputStream(file));
					Map<String, User> map = (Map<String, User>) ois.readObject();
					System.out.println(">>>" + map);

					Set<String> keysets = map.keySet();// 获取集合中的键
					boolean bln = false;

					for (String key : keysets) {
						if (key.startsWith(input)) {// 得到的集合中有以输入的账号开头的
							Object obj = map.get(key);
							User temp = (User) obj;// 将得到集合转成User
							change(temp, file, map);
							bln = true;
							break;
						}
					}
					if (!bln) {
						System.out.println("用户不存在");
					}

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void change(User temp, File file, Map<String, User> map) {
		while (true) {

			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("text/userInfo.txt")));
				map = (HashMap<String, User>) ois.readObject();

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("请选择要修改的信息:(1.密码 2.姓名 3.学历 4.地址)");
			String input = scanner.next();

			if (input.equals("1")) {
				System.out.println("请输入新密码(至少8位):");
				String newPassword = scanner.next();
				temp.setUserPassword(newPassword);

			} else if (input.equals("2")) {
				System.out.println("请输入新姓名(长度小于10)");
				String newName = scanner.next();
				temp.setUserName(newName);

			} else if (input.equals("3")) {
				while (true) {

					System.out.println("请输入修改后的学历(1.小学2.中学3.大学4.其他)");
					String newEducation = scanner.next();
					if (newEducation.equals("1")) {
						newEducation = "小学";
						temp.setEdu(newEducation);
						break;

					} else if (newEducation.equals("2")) {
						newEducation = "中学";
						temp.setEdu(newEducation);
						break;

					} else if (newEducation.equals("3")) {
						newEducation = "大学";
						temp.setEdu(newEducation);
						break;

					} else if (newEducation.equals("4")) {
						newEducation = "其他";
						temp.setEdu(newEducation);
						break;
					} else {
						System.out.println("输入错误，只能输入1-4");
					}
				}

			} else if (input.equals("4")) {
				System.out.println("请输入修改后的地址：");
				String newAddress = scanner.next();
				temp.setAddress(newAddress);
			} else if ("5".equals(input)) {
				System.out.println("账号不存在，请重新输入");
				changeAccount();
			} else if ("6".equals(input)) {
				System.out.println("返回上一级");
				ManageMenuBusiness mmb = new ManageMenuBusiness();
				mmb.menu();
			}
			System.out.println("1.确认  2.继续修改");
			String input1 = scanner.next();
			if ("1".equals(input1)) {
				String userAccount = temp.getUserAccount();
				String ID = temp.getID();
				String newkey = userAccount + ID;
				map.put(newkey, temp);
				System.out.println("修改成功！");
				ObjectOutputStream oos;
				try {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(map);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if ("2".equals(input1)) {
				// change(User temp, File file, Map<String, User> map);
			} else {
				System.out.println("输入错误");
			}
			ManageMenuBusiness mmb = new ManageMenuBusiness();
			mmb.menu();
		}
		// map.put(newPasswozrd,newName,newEducation,newAddress);
	}

}
