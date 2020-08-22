package bankingapp.BankingApplication;

import java.util.ArrayList;

public class Customer {
	
	private String firstName, lastName;
	
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
