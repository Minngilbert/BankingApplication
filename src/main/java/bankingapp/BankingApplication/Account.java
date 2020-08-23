package bankingapp.BankingApplication;

import java.util.ArrayList;

public class Account {
	
	private Customer member;
	private int accountId;
	private double balance;
	private boolean pendingApproval;
	
	static ArrayList<Account> activeAccounts = new ArrayList<Account>();
	static ArrayList<Account> pendingAccounts = new ArrayList<Account>();
	

	public Account(Customer owner, int accountId, boolean pendingApproval) {
		this.member = owner;
		this.accountId = accountId;
		this.balance = 0; //Start with a balance of zero
		this.pendingApproval = pendingApproval;
		this.pendingAccounts.add(this);
	}
	
	public Account(int accountId, boolean pendingApproval) {
		this.accountId = accountId;
		this.balance = 0; //Start with a balance of zero
		this.pendingApproval = pendingApproval;
		this.pendingAccounts.add(this);
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
	public void addAcctiveAccount(Account activeAccount) {
		this.activeAccounts.add(activeAccount);
	}
	
	public ArrayList<Account> getActiveAccounts() {
		return activeAccounts;
	}
	

	
}
