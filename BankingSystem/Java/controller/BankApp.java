package com.ty.controller;

import java.util.Scanner;

import com.ty.dao.bankDao;

public class BankApp {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		bankDao dao=new bankDao();
		
		while(true) {
			
			System.out.println("========Banking System");
			System.out.println("1.  Create Bank");
			System.out.println("2.  Create Account");
			System.out.println("3.  Login");
			System.out.println("4.  Delete Account");
			System.out.println("5.  Delete bank");
			System.out.println("6.  Exit");
			System.out.println(".....Enter your choice");
			int choice=sc.nextInt();
			
			switch(choice) {
			
			case 1:
				dao.CrrateBank();
				break;
				
			case 2:
				dao.createAccount();
				break;
				
			case 3:
				dao.login();
				break;
			
			case 4:
				dao.deleteAccount();
				break;
			case 5:
				dao.createAccount();
				break;
				
			case 6:
				System.out.println("your Exit");
				return;
				
			default:
				System.out.println(" Invalid choice");
			}
		}
		// TODO Auto-generated method stub

	}

}
