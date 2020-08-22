package com.BankingApp;

import java.util.ArrayList;

public class Account {
	
	private Customer member;
	private int accountId;
	private double balance;
	private boolean pendingApproval;
	private static int accountGenerator = 1;
	
	ArrayList<Account> pendingAccounts = new ArrayList<Account>();
	
	public Account(Customer owner, int accountId, double balance, boolean pendingApproval) {
		this.member = owner;
		this.accountId = accountId;
		this.balance = balance;
		this.pendingApproval = pendingApproval;
		accountGenerator++;
		this.pendingAccounts.add(this);
	}
	
	public Account(int accountId, double balance, boolean pendingApproval) {
		this.accountId = accountId;
		this.balance = balance;
		this.pendingApproval = pendingApproval;
		accountGenerator++;
		this.pendingAccounts.add(this);
	}
	
	public boolean isPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(boolean pendingApproval) {
		this.pendingApproval = pendingApproval;
	}

	public ArrayList<Account> getPendingAccounts() {
		return pendingAccounts;
	}

	public void setPendingAccounts(ArrayList<Account> pendingAccounts) {
		this.pendingAccounts = pendingAccounts;
	}

	public Customer getMember() {
		return this.member;
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
