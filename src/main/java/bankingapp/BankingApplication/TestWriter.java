package bankingapp.BankingApplication;

public class TestWriter {

	/**
	 * 
	 * Please do not test, I am just here to make sure serialization and deserialization works
	 */
	/*public static void main(String[] args) {
		FileHandler file = new FileHandler();
		Customer c = new Customer("C", "C", "user", "pass");
		c = new Customer("A", "A", "user", "pass");
		System.out.println(Customer.customerList);
		file.writeCustomerFile();
		System.out.println("Emptying list...");
		Customer.customerList.clear();
		System.out.println("After clear : " + Customer.customerList);
		System.out.println("Reading customer file...");
		file.readCustomerFile();
		System.out.println("List after file read: " + Customer.customerList);
		System.out.println(Customer.customerList.get(1));
		
		System.out.println("Testing accounts");
		Account a = new Account();
		a = new Account(c);
		a = new Account(c);
		Account.pendingAccounts.get(1).setPendingApproval(false);
		a = new Account(c);
		Account.pendingAccounts.get(1).setPendingApproval(false);
		a = new Account(c);
		a = new JointAccount(c, new Customer("B","B","joint","joint"));
		System.out.println("Accounts added, testing for write and read");
		file.writeAccountFile();
		System.out.println("Pending accounts: " + Account.pendingAccounts);
		System.out.println("Active accounts: " + Account.activeAccounts);
		System.out.println("Clearing account lists");
		Account.pendingAccounts.clear();
		Account.activeAccounts.clear();
		System.out.println("Reading accounts...");
		file.readAccountFile();
		System.out.println("Pending accounts: " + Account.pendingAccounts);
		System.out.println("Active accounts: " + Account.activeAccounts);
		
		System.out.println("Adding one employee and one administrator");
		Employee e = new Employee("Someone");
		Administrator ad = new Administrator("The badministrator", 1);
		
	}*/

}
