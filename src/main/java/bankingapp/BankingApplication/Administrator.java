package bankingapp.BankingApplication;

public class Administrator extends Employee{
	private String name;
	private int employeeId;
	
	public Administrator(String name, int employeeId) {
		super(name, employeeId);
		
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
