package com.BankingApp;

public interface AccountManipulator {
	public boolean deposit(Account account, double amount);
	public boolean withdraw(Account account,double amount);
	public boolean transfer(Account fromAcc, Account toAcc, double amount);
}
