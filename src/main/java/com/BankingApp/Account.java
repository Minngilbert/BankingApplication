package com.BankingApp;

import java.util.ArrayList;

public class Account {
	private Customer owner;
	private int accountId;
	private double balance;
	private boolean pendingApproval;
	private static int accountGenerator = 1;
	
	
	ArrayList<Account> pendingAccounts = new ArrayList<Account>();
	
	
	public Account(Customer owner, int accountId, double balance, boolean pendingApproval) {
		this.owner = owner;
		this.accountId = accountId;
		this.balance = balance;
		this.pendingApproval = pendingApproval;
		accountGenerator++;
	}
	
	public Customer getOwner() {
		return this.owner;
	}

	public int getAccountId() {
		return this.accountId;
	}
	
	public double getBalance() {
		return this.balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
