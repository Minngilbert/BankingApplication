package bankingapp.BankingApplication;

import java.io.Serializable;

public class Administrator extends Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int employeeId;
	
	public Administrator(String name, int employeeId) {
		super(name);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void cancelAccount(Account account) {
		account.removeActiveAccount(account);		
	}

}
