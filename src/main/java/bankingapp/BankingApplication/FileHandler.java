package bankingapp.BankingApplication;

import java.io.File;

public class FileHandler {
	private File employeeFile;
	private File accountFile;
	private File customerFile;
	private File userInfoFile;
	
	public FileHandler() {
		employeeFile = new File ("employeelist");
		userInfoFile = new File ("users");
		accountFile = new File ("accountlist");
		customerFile = new File ("customerlest");
	}
	
	public boolean readEmployeeFile() {
		return false;
	}
	public boolean writeEmployeeFile() {
		return false;
	}
	public boolean readUserInfoFile() {
		return false;
	}
	public boolean writeUserInfoFile() {
		return false;
	}
	public boolean readCustomerFile() {
		return false;
	}
	public boolean writeCustomerFile() {
		return false;
	}
	public boolean readAccountFile() {
		return false;
	}
	public boolean writeAccountFile() {
		return false;
	}
}
