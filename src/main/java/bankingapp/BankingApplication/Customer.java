package bankingapp.BankingApplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	private String firstName, lastName, username, password;

	
	public Customer(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		customerList.add(this);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return this.username;
	}

	
	public void viewAccountInformation() {
		Account temp = new Account();
		ArrayList<Account> activeAccounts = temp.getActiveAccounts();
		
		for(Account acc: activeAccounts) {
			if(acc.getMember() == this) {
				System.out.println(acc.getMember().toString());
				System.out.println("Balance : " + acc.getBalance());
			}
		}
	}

	public double deposit(Account account, double amount) {
		double currBalance =  account.getBalance();
		
		if(amount > 0) {
			account.setBalance(currBalance + amount);
		}
		return account.getBalance();
	}

	public double withdraw(Account account, double amount) {
		double currBalance =  account.getBalance();
		
		if(currBalance >= amount) {
			account.setBalance(currBalance - amount);
		}
		return account.getBalance();
	}
	
	public Account getCustomerAccount() {
		Account acc = new Account();
		
		for(Account account : acc.getActiveAccounts()) {
			if(account.getMember().getUsername() == this.username) {
					return account;
			}	
		}
		return acc; //might want to throw exception here
	}
	
	public double getAccountBalance() {
		Account acc = this.getCustomerAccount();
		return acc.getBalance();
	}
	
	public String toString() {
		return "Name: " + firstName + " " + lastName +" Username: " + username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
	@Override
	public void setUsername(String user) {
		this.username = user;
		
	}

}
