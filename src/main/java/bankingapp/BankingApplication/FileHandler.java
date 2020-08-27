package bankingapp.BankingApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
	private File employeeFile;
	private File accountFile;
	private File customerFile;

	public FileHandler() {
		employeeFile = new File("employeelist.list");
		accountFile = new File("accountlist.list");
		customerFile = new File("customerlist.list");
	}

	public boolean readEmployeeFile() {
		if (employeeFile.exists()) {
			System.out.println("Found employees.");
			try {
				FileInputStream fi = new FileInputStream(employeeFile);
				ObjectInputStream  oo = new ObjectInputStream(fi);
				/*int len = oo.readInt();
				for(int i=0;i<len;i++) {
					Customer.customerList.add((Customer)oo.readObject());
				}*/
				Employee.employeeList = (ArrayList<Employee>) oo.readObject();
				oo.close();
				fi.close();
				return true;
			}catch (IOException e) {
				System.out.println("No file read occurred for employees");
			} catch (ClassNotFoundException e) {
				
			}
		}
		return false;
	}

	public boolean writeEmployeeFile() {
		if (!Employee.employeeList.isEmpty()) {
			System.out.println("Found employee file.");
			try {
				FileOutputStream fo = new FileOutputStream(employeeFile);
				ObjectOutputStream  oo = new ObjectOutputStream(fo);
				/*oo.writeInt(Customer.customerList.size());
				for(int i=0;i<Customer.customerList.size();i++) {
					oo.writeObject(Customer.customerList.get(i));
				}*/
				oo.writeObject(Employee.employeeList);
				oo.close();
				fo.close();
			}catch (IOException e) {
				System.out.println("No file write occurred for user info");
			}
		}
		System.out.println("User list was empty.");
		return false;
	}

	public boolean readCustomerFile() {
		if (customerFile.exists()) {
			System.out.println("Found customers.");
			try {
				FileInputStream fi = new FileInputStream(customerFile);
				ObjectInputStream  oo = new ObjectInputStream(fi);
				/*int len = oo.readInt();
				for(int i=0;i<len;i++) {
					Customer.customerList.add((Customer)oo.readObject());
				}*/
				Customer.customerList = (ArrayList<Customer>) oo.readObject();
				oo.close();
				fi.close();
			}catch (IOException e) {
				System.out.println("No file read occurred for user info");
			} catch (ClassNotFoundException e) {
				
			}
		}
		System.out.println("No user info file found.");
		return false;
	}

	public boolean writeCustomerFile() {
		if (!Customer.customerList.isEmpty()) {
			System.out.println("Found customers.");
			try {
				FileOutputStream fo = new FileOutputStream(customerFile);
				ObjectOutputStream  oo = new ObjectOutputStream(fo);
				/*oo.writeInt(Customer.customerList.size());
				for(int i=0;i<Customer.customerList.size();i++) {
					oo.writeObject(Customer.customerList.get(i));
				}*/
				oo.writeObject(Customer.customerList);
				oo.close();
				fo.close();
			}catch (IOException e) {
				System.out.println("No file write occurred for customers");
			}
		}
		System.out.println("Customer list was empty.");
		return false;
	}

	public boolean readAccountFile() {
		if (accountFile.exists()) {
			System.out.println("Found accounts.");
			try {
				FileInputStream fi = new FileInputStream(accountFile);
				ObjectInputStream  oo = new ObjectInputStream(fi);
				/*int len = oo.readInt();
				for(int i=0;i<len;i++) {
					Customer.customerList.add((Customer)oo.readObject());
				}*/
				Account.pendingAccounts = (ArrayList<Account>) oo.readObject();
				Account.activeAccounts = (ArrayList<Account>) oo.readObject();
				oo.close();
				fi.close();
			}catch (IOException e) {
				System.out.println("No file read occurred for user info");
			} catch (ClassNotFoundException e) {
				
			}
		}
		System.out.println("No user info file found.");
		return false;
	}

	public boolean writeAccountFile() {
		if (!Account.pendingAccounts.isEmpty() || !Account.activeAccounts.isEmpty()) {
			System.out.println("Found accounts.");
			try {
				FileOutputStream fo = new FileOutputStream(accountFile);
				ObjectOutputStream  oo = new ObjectOutputStream(fo);
				/*oo.writeInt(Customer.customerList.size());
				for(int i=0;i<Customer.customerList.size();i++) {
					oo.writeObject(Customer.customerList.get(i));
				}*/
				oo.writeObject(Account.pendingAccounts);
				oo.writeObject(Account.activeAccounts);
				oo.close();
				fo.close();
			}catch (IOException e) {
				System.out.println("No file write occurred for customers");
			}
		}
		
		return false;
	}

	public boolean checkForAdminAccounts() {
		if (!employeeFile.exists())
			return false;
//		if (!userInfoFile.exists()) return false;
//		if (!accountFile.exists()) return false;
//		if (!customerFile.exists()) return false;

		return true;
	}
}
