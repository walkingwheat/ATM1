package com.feicui.atm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ManageMenuBusiness {
	
	Scanner scanner = new Scanner(System.in);
	
	public void menu() {
		
		File file = new File("text\\manageMenu.txt");
		FileReader fr = null;
		System.out.println("请选择操作");
		
		try {
			fr = new FileReader(file);
			char[] charArr = new char[50];
			int count = 0;
			while((count = fr.read(charArr))!= -1) {
				System.out.println(charArr);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fr!= null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 
		String input = scanner.next();
		if(input.equals("1")) {
			AddAccountBusiness aab = new AddAccountBusiness();
			aab.addAccount();
		}else if(input.equals("2")) {
			AddAccountBusiness aab = new AddAccountBusiness();
			aab.deleteAccount();
		}else if(input.equals("3")) {
			AddAccountBusiness aab = new AddAccountBusiness();
			aab.showAccount();
		}else if(input.equals("4")) {
			AddAccountBusiness aab = new AddAccountBusiness();
			aab.changeAccount();
		}else if("5".equals(input)) {
			
		}else if("6".equals(input)) {
			LoginBusiness lb = new LoginBusiness();
			lb.showLogin();
		}
	}
	
}
