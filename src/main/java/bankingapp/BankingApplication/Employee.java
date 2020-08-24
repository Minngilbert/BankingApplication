package bankingapp.BankingApplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends User implements Serializable {

	private String name;
	private int employeeId;
	private String username, password;
	private static int employeeIdGenerator = 0;
	public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	public Employee(String name) {
		this.name = name;
		employeeId=employeeIdGenerator++;
		employeeList.add(this);
	}

	public String getName() {
		return name;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void viewAccountInformation(int accountId) {
		Account temp = new Account();
		ArrayList<Account> activeAccounts = temp.getActiveAccounts();

		for (Account acc : activeAccounts) {
			if (acc.getAccountId() == accountId) {
				System.out.println(acc.getMember().toString());
				System.out.println("Balance : " + acc.getBalance());
			}
		}
	}

	public double deposit(Account account, double amount) {
		double currBalance = account.getBalance();

		if (amount > 0) {
			account.setBalance(currBalance + amount);
		}
		return account.getBalance();
	}

	public double withdraw(Account account, double amount) {
		double currBalance = account.getBalance();

		if (currBalance >= amount) {
			account.setBalance(currBalance - amount);
		}
		return account.getBalance();
	}

	public boolean transfer(Account fromAcc, Account toAcc, double amount) {
		double beforeTranFr = fromAcc.getBalance();
		double beforeTranTo = toAcc.getBalance();

		deposit(toAcc, amount);
		withdraw(fromAcc, amount);

		double currBalanceFr = fromAcc.getBalance() - amount;
		double currBalanceTo = toAcc.getBalance() + amount;

		// check to make sure balance was updated
		if ((beforeTranFr == currBalanceFr) && (beforeTranTo == currBalanceTo)) {
			return true;
		}

		return false;
	}

	public void getPendingAccounts() {
		Account acc = new Account();
		for (Account account : acc.getPendingAccounts()) {
			System.out.println(account.getAccountId());
		}
	}

	/*
	 * returns true if the account was approved false if account was not updated
	 * remove account from the arrayList of Pending accounts add to active accounts
	 */
	public boolean makeAccountActive(int accountId) {
		Account acc = new Account();

		for (Account account : acc.getPendingAccounts()) {
			if (account.getAccountId() == accountId) {
				/*
				 * account is set to true if its pending approval check if account is pending
				 */
				if (((account.isPendingApproval() == true))) {
					account.setPendingApproval(false);
					account.getActiveAccounts().add(account);
					account.getPendingAccounts().remove(account);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setUsername(String user) {
		username = user;
	}
}
