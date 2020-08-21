package com.BankingApp;

import java.util.ArrayList;



public class Customer implements AccountManipulator {
	ArrayList<Account> Accounts;
	Account acct;	
	public Customer(String name) {
		Accounts = new ArrayList<Account>();
		acct = Accounts.get(0); 
	}
	

	public Object registerForAccount(int AccountNumber) {
		return null;
	}
	
	public void deposit(int Account,int amount) {
		
	}
	

	public void withdraw(int Account,int amount) {
		
	}
	
	public void transfer(int Account,int amount) {
		
	}
	


}
