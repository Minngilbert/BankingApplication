package bankingapp.BankingApplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Customer member;
	private int accountId;
	private double balance;
	private boolean pendingApproval;
	private static int accountGenerator=0;
	
	public static ArrayList<Account> activeAccounts = new ArrayList<Account>();
	public static ArrayList<Account> pendingAccounts = new ArrayList<Account>();
	

	public Account(Customer owner) {
		this.member = owner;
		this.accountId = accountGenerator++;
		this.balance = 0; //Start with a balance of zero
		this.pendingApproval = true;
		pendingAccounts.add(this);
		
	}
	
	public Account() {
		super();
	}
	
	public boolean isPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(boolean pendingApproval) {
		this.pendingApproval = pendingApproval;
		if(pendingApproval == false)
			promoteAccountToActive(this);
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
	
	public void removePendingAccount(Account account) {
		pendingAccounts.remove(account);
	}
	
	public void promoteAccountToActive(Account account) {
		pendingAccounts.remove(account);
		activeAccounts.add(account);
	}
	
	public ArrayList<Account> getPendingAccounts() {
		return pendingAccounts;
	}
	
	public void removeActiveAccount(Account account) {
		activeAccounts.remove(account);
	}
	
	//adds to active accounts array
	public void addActiveAccount(Account activeAccount) {
		activeAccounts.add(activeAccount);
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
