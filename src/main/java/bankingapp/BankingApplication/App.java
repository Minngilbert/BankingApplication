package bankingapp.BankingApplication;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	private Scanner cons = new Scanner(System.in);
	private Logger log = LogManager.getLogger(App.class);
	FileHandler files = new FileHandler();

	public static void main(String[] args) {
		/*
		 * Employee e1 = new Employee("Jack", 9092342); Administrator e2 = new
		 * Administrator("Mary", 1234);
		 * 
		 * Customer c1 = new Customer("firstName", "lastName", "username", "password");
		 * c1.registerForAccount(123); Customer c2 = new Customer("firstName2",
		 * "lastName2", "username2", "password2");
		 * 
		 * Account account = new Account();
		 * System.out.println(account.getPendingAccounts().size());
		 * 
		 * JointAccount join = new JointAccount(c1, c2, 890);
		 * 
		 * for (Account acc : account.getPendingAccounts()) { acc.showAccountInfo(); }
		 */

		App app = new App();
		app.startUp();

	}

	public void startUp() {
		System.out.println("Welcome to the Bank of The Town With No Name\n");

		if (!files.checkFiles()) {
			System.out.println("No data found, please create an administrator account.");
			createAdministratorAccount();
		}

		showMainMenu();
	}

	public void showMainMenu() {
		System.out.println("Please select from one of the following options:");
		boolean valid = false;
		int choice = 0;
		while (!valid) {
			try {
				System.out.println("Enter 1 to sign in as a customer.");
				System.out.println("Enter 2 to sign in as an employee.");
				System.out.println("Enter 3 to sign in as an administrator.");
				System.out.println("Enter 4 to exit");
				choice = cons.nextInt();
				if (choice < 1 || choice > 4)
					throw new NumberFormatException();
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
		switch (choice) {
		case 1:
			doCustomerLogin();
			break;
		case 2:
			doEmployeeLogin();
			break;
		case 3:
			doAdministratorLogin();
			break;
		case 4:
			System.exit(0);
		}
	}

	public void doCustomerLogin() {
		System.out.println("You are logging in as a customer.");
		int choice = 0;
		while (choice < 1 || choice > 3) {
			try {
				System.out.println(
						"Please enter 1 to sign in with an existing account, or enter 2 to sign up for a new account. Press 3 to go back to the main menu.");
				choice = Integer.parseInt(cons.nextLine());

				switch (choice) {
				case 1:
					loginCustomer();
					break;
				case 2:
					makeNewCustomerAccount();
					break;
				case 3: // we do this here so as to not get the call stack too deep, and just fall back
						// to the menu
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
		showMainMenu();
	}

	public void loginCustomer() {
		if (Customer.customerList.isEmpty()) {
			System.out.println("Congratulations, you are our first customer.  Please create a new user account.");
			makeNewCustomerAccount();
			System.out.println("Successfully created new account.  Please log in.");
		}
		Customer cust = null;
		boolean validEntry = false;
		while (!validEntry) {
			String entry;
			System.out.println("Please enter your username");
			entry = cons.nextLine();
			for (Customer c : Customer.customerList) {
				if (c.getUsername().equals(entry)) {
					cust = c;
					break;
				}
			}
			System.out.println("Please enter your password");
			String pass = cons.nextLine();
			if (pass.equals(cust.getPassword()))
				validEntry = true;
		}
		System.out.println("Authorized.");
		showCustomerMenu(cust);
	}

	public void showCustomerMenu(Customer c) {
		Account a = null;
		while (a == null) {
			System.out.println("Welcome, " + c.getFirstName());
			a = findAnAccount(c);
			if (a == null) {
				System.out.println("There are no Active bank accounts yet.  Creating a new one.");
				a = createNewBankAccount(c);
			} else
				System.out.println("Welcome back, " + c.getFirstName() + ".");

			if (a == null) {
				System.out.println("Account creation failed.");
			}
		}
		showCustomerAccountActions(c, a);
	}

	public void showCustomerAccountActions(Customer c, Account a) {
		if (a.isPendingApproval())
			System.out.println("Account ID " + a.getAccountId() + " for Customer " + a.getMember().getFirstName() + " "
					+ a.getMember().getLastName() + " is pending approval and cannot be acted on yet.");
		else {
			boolean isDone = false;
			while (!isDone) {
				System.out.println("Please choose from one of the following options: ");
				System.out.println("Enter 1 to make a deposit");
				System.out.println("Enter 2 to withdraw funds");
				System.out.println("Enter 3 to transfer funds between accounts");
				System.out.println("Enter 4 to sign out.");
				try {
					int choice = Integer.parseInt(cons.nextLine());
					switch (choice) {
					case 1:
						doDeposit(a);
						break;
					case 2:
						doWithdrawal(a);
					case 3:
						doTransfer(a);
					case 4:
						isDone = true;
					default:
						System.out.println("Invalid selection.");
					}
				} catch (NumberFormatException e) {

				}
			}
		}
	}

	public double doDeposit(Account a) {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to deposit");
				double amount = Double.parseDouble(cons.nextLine());
				if (amount < 0.01) {
					System.out.println("Cannot deposit less than one cent.");
				} else {
					a.setBalance(a.getBalance() + amount);
					System.out.println(
							"Successfully deposited $" + amount + " to account.  New balance is $" + a.getBalance());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	public double doWithdrawal(Account a) {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to withdraw");
				double amount = Double.parseDouble(cons.nextLine());
				if (amount < 0.01) {
					System.out.println("Cannot withdraw less than one cent.");
				} else {
					a.setBalance(a.getBalance() - amount);
					System.out.println(
							"Successfully withdrew $" + amount + " to account.  New balance is $" + a.getBalance());
					log.info(new Transaction('t', a, a, a.getBalance() + amount, amount).toString());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	public double doTransfer(Account a) {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to deposit");
				double amount = Double.parseDouble(cons.nextLine());
				if (amount < 0.01) {
					System.out.println("Cannot deposit less than one cent.");
				} else {
					a.setBalance(a.getBalance() + amount);
					System.out.println(
							"Successfully deposited $" + amount + " to account.  New balance is $" + a.getBalance());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	public Account createNewBankAccount(Customer c) {
		if (Account.pendingAccounts.size() + Account.activeAccounts.size() > 1) {
			boolean valid = false;
			while (!valid) {
				System.out.println("Would you like to create a single owner account or a joint account?");
				System.out.println("Enter 1 for single account, enter 2 for joint account.");
				try {
					int choice = Integer.parseInt(cons.nextLine());
					switch (choice) {
					case 1:
						valid = true;
						System.out.println("Creating new bank account...");
						return new Account(c);

					case 2:
						valid = true;
						System.out.println("Creating new joint bank account...");
						return createJointAccount(c);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid selection");
				}
			}

		}
		return new Account(c);
	}

	public Account findAnAccount(User u) {
		for (int i = 0; i < Account.pendingAccounts.size(); i++) {
			if (Account.pendingAccounts.get(i).getMember().getUsername().contentEquals(u.getUsername()))
				return Account.pendingAccounts.get(i);
		}
		for (int i = 0; i < Account.activeAccounts.size(); i++) {
			if (Account.activeAccounts.get(i).getMember().getUsername().contentEquals(u.getUsername()))
				return Account.activeAccounts.get(i);
		}
		return null;
	}

	public Customer findACustomer(String username) {
		for (int i = 0; i < Customer.customerList.size(); i++) {
			if (Customer.customerList.get(i).getUsername().equals(username))
				return Customer.customerList.get(i);
		}
		return null;
	}

	public void makeNewCustomerAccount() {
		Customer cust;
		String[] credentials;
		UserAccount ua;

		credentials = getCustomerCredentials();
		cust = new Customer(credentials[0], credentials[1], credentials[2], credentials[3]);
		System.out.println("Creating new user account for username " + credentials[2]);
		ua = new UserAccount(cust, cust.getUsername(), cust.getPassword());
		System.out.println("Producing new account pending approval...");
	}

	public JointAccount createJointAccount(Customer c1) {
		Customer c2 = null;
		int attemptsRemaining = 3;
		while (attemptsRemaining > 0) {
			System.out.println("Please enter the username of the second account holder (" + attemptsRemaining
					+ " attempt(s) remaining)");
			String user = cons.nextLine();
			c2 = findACustomer(user);
			if (c2 == null) {
				System.out.println("Invalid username, please try again");
				attemptsRemaining--;
				continue;
			}
		}
		if (c2 == null)
			return null;
		return new JointAccount(c1, c2);
	}

	public UserAccount createUserAccount(Customer c, String user, String password) {
		return new UserAccount(c, user, password);
	}

	public String[] getCustomerCredentials() {
		String[] credentials = new String[4];
		boolean choiceValid = false;
		while (!choiceValid) {
			System.out.println("Please enter the First Name of the primary account holder");
			credentials[0] = cons.nextLine();
			System.out.println("Please enter the Last Name of the primary account holder");
			credentials[1] = cons.nextLine();
			if (credentials[0].contains("[^a-zA-Z]") || credentials[0].isEmpty() || credentials[1].isEmpty()
					|| credentials[1].contains("[^a-zA-Z]")) {
				System.out.println("Please enter a valid name");
				continue;
			}
			choiceValid = true;
		}
		choiceValid = false;
		while (!choiceValid) {
			choiceValid = true;
			System.out.println("Please enter a username");
			credentials[2] = cons.nextLine();
			for (int i = 0; i < UserAccount.userList.size(); i++) {
				if (credentials[2].equals(UserAccount.userList.get(i).getUsername())) {
					System.out.println("Username " + credentials[2] + " is already in use.");
					choiceValid = false;
					break;
				}
			}
			System.out.println("Username accepted: " + credentials[2]);
		}
		System.out.println("Please enter the account password: ");
		credentials[3] = cons.nextLine();
		String checkPass = "";
		while (!checkPass.equals(credentials[3])) {
			System.out.println("Please verify your password entered: ");
			checkPass = cons.nextLine();
		}
		return credentials;
	}

	public void doEmployeeLogin() {

	}

	public Employee findAnEmployee(String username) {
		for (int i = 0; i < UserAccount.userList.size(); i++) {
			if (Employee.employeeList.get(i).getUsername().contains("admin"))
				return Employee.employeeList.get(i);
		}
		return null;
	}

	public void createAdministratorAccount() {
		while (true) {
			String username = "";
			while (!username.contains("admin")) {
				System.out.println("Please enter a username (must contain admin somewhere in it");
				username = cons.nextLine();
			}
			System.out.println("Please enter a password");
			String password = cons.nextLine();
			String check = "";
			while (!check.equals(password)) {
				System.out.println("Please confirm your password");
				check = cons.nextLine();
				if (!check.equals(password))
					System.out.println("Mismatch, please re-enter.");
			}
			break;
		}
	}

	public void showAdministratorMenu() {

	}

	public void doAdministratorLogin() {
		Employee admin = null;
		System.out.println("Checking for administrator accounts...");
		admin = findAnEmployee("admin");
		if (admin == null) {
//			System.out.println("Found administrator account.");
//			while(true) {
//				System.out.println("Please enter your username.");
//				String username = cons.nextLine();
//				System.out.println("Please enter your password");
//				showAdministratorMenu();
//			}
			System.out.println("No administrator accounts found.  Please create one.")
		}

	}
}
