package com.ty.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.dto.Account;
import com.ty.dto.Bank;

public class bankDao {
	
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("avinash");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Scanner sc=new Scanner(System.in);
	
	public void CrrateBank() {
		
		Bank b=new Bank();
		
		System.out.println("Enter your bank id");
		b.setBid(sc.nextInt());
		System.out.println("Enter your bank name");
		b.setBname(sc.next());
		System.out.println("Enetr your branch name");
		b.setBranch(sc.next());
		
		et.begin();
		em.persist(b);
		et.commit();
		
		System.out.println("your bank created succefully");
		
	}
	public void createAccount() {
		
		System.out.println("Enter your bank id");
		int BankID=sc.nextInt();
		
		Bank b=em.find(Bank.class, BankID);
		
		if(b ==null) {
			System.out.println("Bank is not found........");
			return;
		}
		
		Account acc=new Account();
		System.out.println("Enter your Account holder name...");
		acc.setName(sc.next());
		System.out.println("set the pin for This account ");
		acc.setPin(sc.nextInt());
		
		acc.setBalnace(0);
		acc.setBank(b);
		et.begin();
		em.persist(acc);
		et.commit();
		System.out.println("Your account is created succefully in "+b.getBname());
		
	}
	
	public void login() {
		System.out.println("Enter your Account number");
		int no=sc.nextInt();
		Account acc=em.find(Account.class, no);
		
		if(acc!=null) {
			System.out.println("Enter your pin");
			int pin=sc.nextInt();
			if(pin==acc.getPin()) {
				
				System.out.println("*********welcome  "+acc.getName()+"*******");
				while(true) {
					
					 System.out.println("\n--- ACCOUNT MENU ---");
	                 System.out.println("1. Deposit");
	                 System.out.println("2. Withdraw");
	                 System.out.println("3. Check Balance");
	                 System.out.println("4. Transfer money");
	                 System.out.println("5  Logout");
	                 System.out.print("Enter choice: ");
	                 
	                 int choice=sc.nextInt();
	                 
	                 switch (choice) {
	                	 case 1:
	                		 
	                		 System.out.println("Enter your amount");
	                		 int amountd=sc.nextInt();
	                		 if(amountd<=0) {
	                			 System.out.println("invalid amount");
	                			 break;
	                		 }
	                		 et.begin();
	                		 acc.setBalnace(acc.getBalnace()+amountd);
//	                		 em.merge(acc);
	                		 et.commit();
	                		 
	                		 break;
	                		
	                	 case 2:
	                		 System.out.println("Enter your amount");
	                		 int amountw=sc.nextInt();
	                		 if(amountw>acc.getBalnace()) {
	                			 System.out.println("Insufficient balance");
	                			 break;
	                		 }
	                		 et.begin();
	                		 acc.setBalnace(acc.getBalnace()-amountw);
//	                		 em.merge(acc);
	                		 et.commit();
	                		 break;
	                	 case 3:
	                		 System.out.println("Available balance is "+acc.getBalnace());
	                		 break;
	                		 
	                	 case 4:
	                		 System.out.println("Enter bamk id to transfer");
	                		 int id=sc.nextInt();
	                		 Query q=em.createQuery("select bank from Bank bank");
	                		 List<Bank>b=q.getResultList();
	                		 for(Bank b1:b) {
	                			 if(id==b1.getBid()) {
	                				 System.out.println("Enter Account Number To Transfer:");
	                				 int aco=sc.nextInt();
	                				 System.out.println("Enter amount");
	                				 int amountT=sc.nextInt();
	                				 List<Account>a=b1.getAcount();
	                				 for(Account a1:a) {
	                					 if(aco==a1.getAno()) {
	                						 if(amountT<=acc.getBalnace()) {
	                							 et.begin();
	                							 a1.setBalnace(a1.getBalnace()+amountT);
	                							 acc.setBalnace(acc.getBalnace()-amountT);
	                							 em.merge(a1);
	                							 em.merge(acc);
	                							 et.commit();
	                							 System.out.println("Transfer successful!");
	                							 break;
	                							 
	                						 }else {
	                							 System.out.println("Insufficient Balance");
	                							 break;
	                						 }
	                						 
	                					 }else {
	                						 System.out.println("Account not found");
	                						 
	                					 }
	                				 }
	                				 
	                			 }else {
	                				 System.out.println("Invalid ID");
	                				 
	                			 }
	                		 }
	                		 break;
	                	 case 5:
	                		 System.out.println("Loged out Succefully");
	                		 return;
	                	default:
	                		System.out.println("Enetr vaild choice");
	                		break;
	                		
	                	
	                		 
	                	 
	                 }
				}
				
                 
			}else {
				System.out.println("pin is wrong");
			}
			
			
			
		}else {
			System.out.println("Your Account not found");
		}
		
		
	}
	
	public void deleteAccount() {
		
		System.out.println("Enter account number");
		int no=sc.nextInt();
		
		Account acc=em.find(Account.class, no);
		if(acc==null) {
			System.out.println("account not exist");
		}
		et.begin();
		em.remove(acc);
		et.commit();
	}
	
	public void DeletBank() {
		System.out.println("Enter Bank id to Delete");
		int id=sc.nextInt();
		Bank b=em.find(Bank.class, id);
		if(b==null) {
			System.out.println("Bank is not exist");
		}
		Query q=em.createQuery("select acc from Account acc");
		List<Account>acc=q.getResultList();
		
		et.begin();
		for(Account a:acc) {
			if(id==a.getAno()) {
				em.remove(a);
			}
		
		}
		em.remove(acc);
		et.commit();
		
	
	
	}
}

	


