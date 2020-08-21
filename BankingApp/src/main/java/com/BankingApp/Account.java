package com.BankingApp;

public class Account {
	
	private String owner;
	private int accountId;
	private double balance;
	private static int accountGenerator = 1;
	
	public Account(String owner, int accountId, double balance) {
		this.owner = owner;
		this.accountId = accountId;
		this.balance = balance;
		accountGenerator++;
	}
	
	public String getOwner() {
		return this.owner;
	}

	public int getAccountId() {
		return this.accountId;
	}
	
	public double getBalance() {
		return this.balance;
	}

	
}
