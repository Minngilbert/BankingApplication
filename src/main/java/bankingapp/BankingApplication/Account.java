package bankingapp.BankingApplication;

import java.util.ArrayList;

public class Account {
	
	private Customer member;
	private int accountId;
	private double balance;
	private boolean pendingApproval;
	private static int accountGenerator;
	
	static ArrayList<Account> activeAccounts = new ArrayList<Account>();
	static ArrayList<Account> pendingAccounts = new ArrayList<Account>();
	

	public Account(Customer owner, int accountId) {
		this.member = owner;
		this.accountId = accountId;
		this.balance = 0; //Start with a balance of zero
		this.pendingApproval = true;
		this.pendingAccounts.add(this);
		accountGenerator++;
	}
	
	public Account() {
		super();
	}
	
	public boolean isPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(boolean pendingApproval) {
		this.pendingApproval = pendingApproval;
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
	
	public Customer getMember() {
		return this.member;
	}
	
	public ArrayList<Account> getPendingAccounts() {
		return pendingAccounts;
	}
	
	public void removeActiveAccount(Account account) {
		activeAccounts.remove(account);
	}
	
	//adds to active accounts array
	public void addActiveAccount(Account activeAccount) {
		this.activeAccounts.add(activeAccount);
	}
	
	public ArrayList<Account> getActiveAccounts() {
		return activeAccounts;
	}
	
	public String toString() {
		return member.getFirstName() + " " + member.getLastName() +" "+ this.accountId + "  " + this.balance; 
	}
	
	public void showAccountInfo() {
		System.out.println(this.toString());
	}

	
}
