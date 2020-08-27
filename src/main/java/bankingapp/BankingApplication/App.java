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
	private Administrator placeHolderAdmin = new Administrator("John", "Doe", "Invalid", "");
	private Customer placeHolderCustomer = new Customer("John", "Doe", "Invalid", "");
	private Employee placeHolderEmployee = new Employee("Jane", "Doe", "Invalid", "");

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

	// initial greetings and data check
	public void startUp() {
		System.out.println("Welcome to the Bank of The Town With No Name\n");

		Customer.customerList.clear();
		Employee.employeeList.clear();

		if (!files.checkForAdminAccounts()) {
			System.out.println("No administrator data found, please create an administrator account.");
			createAdministratorAccount();
		}

		if (!files.readAccountFile())
			System.out.println("Couldn't read account file.");
		if (!files.readCustomerFile())
			System.out.println("Couldn't read customer file.");
		if (!files.readEmployeeFile()) {
			System.out.println("Couldn't read employee file.");
		}
		showMainMenu();
	}

	// startup / main menu when an account exists
	public void showMainMenu() {
		System.out.println("Please select from one of the following options:");
		boolean done = false;
		int choice = 0;
		while (!done) {
			try {
				System.out.println("Enter 1 to sign in as a customer.");
				System.out.println("Enter 2 to do employee actions.");
				System.out.println("Enter 3 to exit");
				choice = Integer.parseInt(cons.nextLine());
				if (choice < 1 || choice > 3)
					throw new NumberFormatException();
				switch (choice) {
				case 1:
					doCustomerLogin();
					break;
				case 2:
					showEmployeeActions();
					break;
				case 3:
					done = true;
					System.out.println("Thank you for using this banking software.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
		files.writeCustomerFile();
		files.writeEmployeeFile();
		files.writeAccountFile();
	}

	public void showEmployeeActions() {
		System.out.println("Please select from one of the following options:");
		boolean done = false;
		int choice = 0;
		while (!done) {
			try {
				System.out.println("Enter 1 to sign in as an employee.");
				System.out.println("Enter 2 to sign in as an administrator.");
				System.out.println("Enter 3 to create a new employee account");
				System.out.println("Enter 4 to create a new administrator account");
				System.out.println("Enter 5 to go back to the main menu");
				choice = Integer.parseInt(cons.nextLine());
				if (choice < 1 || choice > 5)
					throw new NumberFormatException();
				switch (choice) {
				case 1:
					doEmployeeLogin();
					break;
				case 2:
					doAdminLogin();
					break;
				case 3:
					if (verifyAdminAuthorization())
						createEmployeeAccount();
					else {
						System.out.println("Couldn't successfully authorize administrator account.");
					}
					break;
				case 4:
					if (verifyAdminAuthorization())
						createAdministratorAccount();
					else {
						System.out.println("Couldn't successfully authorize administrator account.");
					}

					break;
				case 5:
					done = true;
					System.out.println("Going back to main menu.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}
		}
		files.writeCustomerFile();
		files.writeEmployeeFile();
		files.writeAccountFile();
	}

	// verifies an admin's ok to create an employee or administrator account
	public boolean verifyAdminAuthorization() {
		while (true) {
			System.out.println("Please enter administrator username.");
			String username = cons.nextLine();
			Administrator a = (Administrator) findAnEmployee(username);
			if (!username.contains("admin") || !username.equals(a.getUsername()) || a == null) {
				System.out.println(
						"Invalid username, please enter a valid admin account (must contain the word admin in it)");
				continue;
			}
			System.out.println("Username accepted.");
			int count = 3;
			boolean valid = false;
			while (count > 0) {
				System.out.println("Please enter your password");
				String password = cons.nextLine();
				if (password.equals(a.getPassword())) {
					valid = true;
					break;
				}
				System.out.println("Invalid password, please re-enter (" + count + " attempt(s) remaining)");
				count--;
			}
			if (valid) {
				System.out.println("Authorized.  Please proceed");
				return true;
			} else {
				System.out.println("Failed to log in.  Returning to menu...");
				return false;
			}
		}
	}

	// validate customer credentials for login
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
		// showMainMenu();
	}

	// log in customer
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
			if (cust == null) {
				System.out.println("Invalid account name.");
				continue;
			}
			System.out.println("Please enter your password");
			String pass = cons.nextLine();
			if (pass.equals(cust.getPassword()))
				validEntry = true;
		}
		System.out.println("Authorized.");
		showCustomerBankAccountOptionsMenu(cust);
	}

	// show customer action menu
	public void showCustomerBankAccountOptionsMenu(Customer c) {
		Account a = null;
		while (a == null) {
			System.out.println("Welcome, " + c.getFirstName());
			a = findAnAccount(c);
			if (a == null) {
				System.out.println("There are no Active bank accounts yet.  Creating a new one.");
				a = createNewBankAccount(c);
			} else {
				System.out.println("Welcome back, " + c.getFirstName() + ".");
				System.out.println("Account information: " + a.toString());
			}
			if (a == null) {
				System.out.println("Account creation failed.");
			}
		}
		showAccountActionMenu(c, a);
	}

	// shows a list of available actions for a customer account
	/*
	 * public void showCustomerAccountActions(Customer c, Account a) { if
	 * (a.isPendingApproval()) System.out.println("Account ID " + a.getAccountId() +
	 * " for Customer " + a.getMember().getFirstName() + " " +
	 * a.getMember().getLastName() +
	 * " is pending approval and cannot be acted on yet."); else { boolean isDone =
	 * false; while (!isDone) {
	 * System.out.println("Please choose from one of the following options: ");
	 * System.out.println("Enter 1 to make a deposit");
	 * System.out.println("Enter 2 to withdraw funds");
	 * System.out.println("Enter 3 to transfer funds between accounts");
	 * System.out.println("Enter 4 to sign out."); try { int choice =
	 * Integer.parseInt(cons.nextLine()); switch (choice) { case 1: doDeposit(a);
	 * break; case 2: doWithdrawal(a); case 3: doTransfer(a); case 4: isDone = true;
	 * default: System.out.println("Invalid selection."); } } catch
	 * (NumberFormatException e) {
	 * 
	 * } } } }
	 */

	// deposits an amount to an Account
	public double doDeposit(User u, Account a) {
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
					valid = true;
					log.info(new Transaction('d', a, a, a.getBalance() + amount, amount, u).toString());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	// withdraw an amount from an account
	public double doWithdrawal(User u, Account a) {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the amount you wish to withdraw");
				double amount = Double.parseDouble(cons.nextLine());
				if (amount < 0.01) {
					System.out.println("Cannot withdraw less than one cent.");
				} else if (amount > a.getBalance()) {
					System.out.println("Cannot overdraw an account.");
				} else {
					valid = true;
					a.setBalance(a.getBalance() - amount);
					System.out.println(
							"Successfully withdrew $" + amount + " to account.  New balance is $" + a.getBalance());
					log.info(new Transaction('t', a, a, a.getBalance() + amount, amount, u).toString());
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	// transfer balances between accounts
	public double doTransfer(User u, Account a) {
		boolean valid = false;
		while (!valid) {
			try {
				System.out.println("Please enter the username of the account you wish to deposit to");
				String username = cons.nextLine();
					
				if(username.isEmpty()) {
				System.out.println("Please enter a valid username");
				continue;
				}
					
				Customer c = findACustomer(username);	
				if(c != null) {
					Account ac = findAnAccount(c);
					System.out.println("Please enter the amount you wish to transfer");
					double amount = Double.parseDouble(cons.nextLine());
						
					if (amount < 0.01) {
						System.out.println("Cannot deposit less than one cent.");
					} else {
						ac.setBalance(ac.getBalance() + amount);
						a.setBalance(a.getBalance() - amount);
						System.out.println(
								"Successfully transfered $" + amount + " to "+ username);
						log.info(new Transaction('t', a, ac, a.getBalance() + amount, amount, u).toString());
						valid = true;
					}
						
				}
				else {
					System.out.println("Please enter a valid username");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input");
			}
		}
		return a.getBalance();
	}

	// for a customer, allows to produce a new account
	// yes, we do allow for a customer to have multiple accounts
	public Account createNewBankAccount(Customer c) {

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
					if (Customer.customerList.size() < 2) {
						System.out.println("Cannot create a joint account: Not enough customers.");
						return null;
					}
					valid = true;
					System.out.println("Creating new joint bank account...");
					return createJointAccount(c);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid selection");
			}

		}
		Account a = new Account(c);
		files.writeAccountFile();
		return a;
	}

	// used to find a bank account based on a username
	public Account findAnAccount(Customer u) {
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

	// walks the list of customers and returns null if nothing is found for account
	// access
	public Customer findACustomer(String username) {
		for (int i = 0; i < Customer.customerList.size(); i++) {
			if (Customer.customerList.get(i).getUsername().equals(username))
				return Customer.customerList.get(i);
		}
		return null;
	}

	// used to create a customer account based on data from getCustomerCredentials
	public void makeNewCustomerAccount() {
		Customer cust = placeHolderCustomer;
		String[] credentials;

		credentials = getAccountCredentials(cust);
		cust = new Customer(credentials[0], credentials[1], credentials[2], credentials[3]);
		System.out.println("Creating new user account for username " + credentials[2]);

		System.out.println("Producing new account pending approval...");
		files.writeCustomerFile();
	}

	// create a new customer joint account
	public JointAccount createJointAccount(Customer c1) {
		Customer c2 = null;
		int attemptsRemaining = 3;
		while (attemptsRemaining > 0) {
			System.out.println("Please enter the username of the second account holder (" + attemptsRemaining
					+ " attempt(s) remaining)");
			String user = cons.nextLine();
			try {
				c2 = findACustomer(user);
				if (c2 == null) {
					attemptsRemaining--;
				} else {
					attemptsRemaining=0;
					break;
				}
			} catch (NullPointerException e) {
				System.out.println("Invalid account entered.");
				attemptsRemaining--;
			}
		}
		try {
			if (c2 == null) {
				System.out.println("Failed to create joint account.");
				return null;
			}
		} catch (NullPointerException e) {
			System.out.println("Failed to create joint account.");
		}
		return new JointAccount(c1, c2);
	}

	// used for defining a new customer
	public String[] getAccountCredentials(User u) {
		String accountVerbage = "";
		if (verifyCustomer(u))
			accountVerbage = "account holder";
		if (verifyEmployee(u))
			accountVerbage = "employee";
		if (verifyAdministrator(u))
			accountVerbage = "administrator";
		String[] credentials = new String[4];
		boolean choiceValid = false;
		while (!choiceValid) {
			System.out.println("Please enter the First Name of " + accountVerbage);
			credentials[0] = cons.nextLine();
			System.out.println("Please enter the Last Name of " + accountVerbage);
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
			if (verifyAdministrator(u)) {
				if (!credentials[2].contains("admin")) {
					System.out.println("Username must contain admin to be an administrator");
					choiceValid = false;
					continue;
				}
			}
			if (verifyCustomer(u)) {
				for (int i = 0; i < Customer.customerList.size(); i++) {
					if (credentials[2].equals(Customer.customerList.get(i).getUsername())) {
						System.out.println("Username " + credentials[2] + " is already in use.");
						choiceValid = false;
						break;
					}
				}
			}
			if (verifyEmployee(u) || verifyAdministrator(u)) {
				for (int i = 0; i < Employee.employeeList.size(); i++) {
					if (credentials[2].equals(Employee.employeeList.get(i).getUsername())) {
						System.out.println("Username " + credentials[2] + " is already in use.");
						choiceValid = false;
						break;
					}
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

	// used for defining a new customer
	/*
	 * public String[] getCustomerCredentials() { String[] credentials = new
	 * String[4]; boolean choiceValid = false; while (!choiceValid) {
	 * System.out.println("Please enter the First Name of account holder");
	 * credentials[0] = cons.nextLine();
	 * System.out.println("Please enter the Last Name of account holder");
	 * credentials[1] = cons.nextLine(); if (credentials[0].contains("[^a-zA-Z]") ||
	 * credentials[0].isEmpty() || credentials[1].isEmpty() ||
	 * credentials[1].contains("[^a-zA-Z]")) {
	 * System.out.println("Please enter a valid name"); continue; } choiceValid =
	 * true; } choiceValid = false; while (!choiceValid) { choiceValid = true;
	 * System.out.println("Please enter a username"); credentials[2] =
	 * cons.nextLine(); for (int i = 0; i < UserAccount.userList.size(); i++) { if
	 * (credentials[2].equals(UserAccount.userList.get(i).getUsername())) {
	 * System.out.println("Username " + credentials[2] + " is already in use.");
	 * choiceValid = false; break; } } System.out.println("Username accepted: " +
	 * credentials[2]); } System.out.println("Please enter the account password: ");
	 * credentials[3] = cons.nextLine(); String checkPass = ""; while
	 * (!checkPass.equals(credentials[3])) {
	 * System.out.println("Please verify your password entered: "); checkPass =
	 * cons.nextLine(); } return credentials; }
	 */

	// find an employee or administrator
	public Employee findAnEmployee(String username) {
		if (Employee.employeeList.isEmpty()) {
			System.out.println("No employees found");
			return null;
		}

		for (int i = 0; i < Employee.employeeList.size(); i++) {
			if (Employee.employeeList.get(i).getUsername().equals(username)) {
				System.out.println("Found employee");
				return Employee.employeeList.get(i);
			}
		}
		System.out.println("What happened here?");
		return null;
	}

	public boolean checkForAnyEmployee() {
		if (Employee.employeeList.size() < 3)
			return false;
		else
			return true;
	}

	public boolean checkForAnyAdministrator() {

		if (checkForAnyEmployee()) {
			for (int i = 0; i < Employee.employeeList.size(); i++) {
				if (Employee.employeeList.get(i).getUsername().contains("admin"))
					return true;
			}
		}
		return false;

	}

	public void createAdministratorAccount() {
		Administrator a = placeHolderAdmin;
		String[] credentials = new String[4];
		credentials = getAccountCredentials(a);
		a = new Administrator(credentials[0], credentials[1], credentials[2], credentials[3]);
		System.out.println(Employee.employeeList.toString());
		files.writeEmployeeFile();
	}

	public void createEmployeeAccount() {
		Employee emp = placeHolderEmployee;
		String[] credentials = getAccountCredentials(emp);

		emp = new Employee(credentials[0], credentials[1], credentials[2], credentials[3]);

		files.writeEmployeeFile();
	}

	public void showAdministratorMenu() {

	}

	public void showAccountActionMenu(User user, Account a) {
		if (a == null) { // came from customer menu as admin or employee
			if (Customer.customerList.size() < 1
					|| Account.activeAccounts.size() + Account.pendingAccounts.size() == 0) {
				System.out.println(
						"No Bank Accounts to act on.  Please let a customer use this program to allow operation.");
				return;
			} else {
				boolean success = false;
				while (!success) {
					System.out.println("Please choose an account to act on");
					System.out.println("Here is a list of available accounts: ");
					System.out.println("Pending accounts");
					for (Account ac : Account.pendingAccounts) {
						System.out.print("ID: " + ac.getAccountId() + " username: " + ac.getMember().getUsername());
					}
					System.out.println();
					System.out.println("Active accounts");
					for (Account ac : Account.activeAccounts) {
						System.out.print("ID: " + ac.getAccountId() + "username: " + ac.getMember().getUsername());
					}
					System.out.println();
					String choice = "";
					while (choice.equals("")) {
						try {
							System.out.println("Please select an account by username to work with.");
							choice = cons.nextLine();
							a = findAnAccount(findACustomer(choice));
						} catch (NullPointerException e) {
							System.out.println("No account found with that username attached.");
							choice = "";
						}
						success = true;
					}
				}
			}
		}
		if (a.isPendingApproval() && user.getClass().equals(Customer.class)) {
			System.out.println("Account ID " + a.getAccountId() + " for Customer " + a.getMember().getFirstName() + " "
					+ a.getMember().getLastName() + " is pending approval and cannot be acted on yet.");
			System.out.println("Would you like to create a new joint account? 1 for yes, 2 for no.");
			while(true) {
				int choice = cons.nextInt();
				if(choice == 1) {
					System.out.println("Going to joint account creation...");
					createJointAccount((Customer)user);
					break;
				}
			}
		}
		else {
			boolean isDone = false;
			while (!isDone) {
				System.out.println("Please choose from one of the following options: ");
				if (verifyEmployee(user) || verifyAdministrator(user)) {
					System.out.println("Enter V to view account information");
					System.out.println("Enter A to view and approve accounts pending approval");
				}
				if (verifyAdministrator(user))
					System.out.println("Enter C to cancel an account");
				if (verifyAdministrator(user) || verifyCustomer(user)) {
					System.out.println("Enter D to make a deposit");
					System.out.println("Enter W to withdraw funds");
					System.out.println("Enter T to transfer funds between accounts");
				}
				System.out.println("Enter Q to sign out.");
				try {
					char choice = cons.nextLine().toLowerCase().charAt(0);
					switch (choice) {
					case 'v':
						if (verifyAdministrator(user)) {
							doAdminAccountView(user, a);
							break;
						}
						if (verifyEmployee((Employee)user)) {
							showAllAccounts(user);
							break;
						}
						System.out.println("Invalid Selection");
						break;
					case 'c':
						if (verifyAdministrator(user)) {
							doAdminCancel(user);
							break;
						}
						System.out.println("Invalid Selection");
						break;
					case 'a':
						if (verifyEmployee(user)) {
							viewPendingAccounts(user);
							break;
						}
						System.out.println("Invalid Selection");
						break;
					case 'd':
						if (!verifyEmployee(user)) {
							doDeposit(user, a);
							break;
						}
						System.out.println("Invalid Selection");
						break;
					case 'w':
						if (!verifyEmployee(user)) {
							doWithdrawal(user, a);
							break;
						}
						System.out.println("Invalid Selection");
						break;
					case 't':
						if (!verifyEmployee(user))
							doTransfer(user, a);
						System.out.println("Invalid Selection");
						break;
					case 'q':
						isDone = true;
						break;
					default:
						System.out.println("Invalid selection.");
					}
				} catch (NumberFormatException e) {

				}
			}
		}
	}

	/* 
	 *************************************************************
	 ******************* Add to main branch***********************
	 ************************** **********************************
	 */
	private  void viewPendingAccounts(User user) {
		if (!verifyAdministrator(user) && !verifyEmployee(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}
		
		Account ac = new Account();
		for(Account a: ac.getPendingAccounts()) {
			System.out.println("First name: "+ a.getMember().getFirstName() +
					", Last name: " + a.getMember().getLastName()
					+ ", username "+ a.getMember().getUsername());
		}
		
		boolean isDone = false;
		while(!isDone) {
			System.out.println("Please enter the user name of the account you want to approve or deny ");
			String username = cons.nextLine();
			
			if(username.isEmpty() || findACustomer(username)== null) {
				System.out.println("The user name entered doesn't belong to a customer");
				continue;
			}
			
			System.out.println("Enter A to approve the pending account");
			System.out.println("Enter D to deny the pending account");
			System.out.println("Enter Q to to exit out of this menu");	
			char choice = cons.nextLine().toLowerCase().charAt(0);
			
			Customer c = findACustomer(username);
			Account a = findAnAccount(c);
			
			if( a != null) {		
				switch(choice) {
				case 'a':
					//approve 
					a.promoteAccountToActive(a);
					break;
				case 'd':
					a.removePendingAccount(a);
					break;
				case 'q':
					isDone = true;
					break;
				default:
					System.out.println("Invalid selection.");
				}
			}
			else {
				System.out.println("The cutomer: "+ c.getFirstName() +" "+c.getLastName()+ " does not have an account");
				continue;
			}
			System.out.println("Do you want to continue approving/ denying accounts enter (y/n)");
			
			if( a.getPendingAccounts().size() == 0 || cons.nextLine().toLowerCase().charAt(0) == 'n') {
				System.out.println("Exiting");
				break;
			}	
		}	
	}

	//currently can only cancel one account at a time
	private boolean doAdminCancel(User user) {
		if (!verifyAdministrator(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return false;
		}
		
		//Shows all account before starting
		showAllAccounts(user);
		
		int attemptsRemaining = 3;
		Administrator adm =(Administrator)user;
		
		while(attemptsRemaining >0) {
				System.out.println("Please enter the username of the account you wish to cancel " 
						+ attemptsRemaining + " attempt(s) remaining)");
				String username = cons.nextLine();
				if(username.isEmpty()) {
					System.out.println("Not a valid input");
					attemptsRemaining--;
					continue;
				}
				Customer c = findACustomer(username);
				if(c == null) {
					System.out.println("No customer with username "+username);
					attemptsRemaining--;
					continue;
				}
				else {
					Account a = findAnAccount(c);
					adm.cancelAccount(a);
					return true;
				}
			}
		
		return false;
	}

	private void showAllAccounts(User user) {
		Account a = new Account();
		if (!verifyEmployee(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}
		for(Account ac: a.getActiveAccounts()) {
			System.out.println("Active: "+ ac.toString());
		}
		for(Account ac: a.getPendingAccounts()) {
			System.out.println("Pending: "+ac.toString());
		}
	}

	private void doAdminAccountView(User user, Account a) {
		if (!verifyAdministrator(user)) {
			System.out.println("Invalid user credentials.  How'd you get here?");
			return;
		}
		Customer c = a.getMember();
		System.out.println(a.toString()); //Has account First/Last Name AccoutnID Balance
		System.out.println("User Name: " + c.getUsername() + "\nPassword" +c.getPassword());
	}
	/* 
	 *************************************************************
	 ******************* 	END **********************************
	 ************************** **********************************
	 */

	public void doEmployeeLogin() {
		User emp = placeHolderEmployee;
		System.out.println("Checking employee accounts...");

		if (checkForAnyEmployee() && Employee.employeeList.size() > 1) {
			System.out.println("Found employee account.");
			while (true) {
				System.out.println("Please enter your username.");
				String username = cons.next();
				emp = findAnEmployee(username);
				// System.out.println(emp.toString());
				if (!username.equals(emp.getUsername()) || emp == null) {
					System.out.println("Invalid username, please enter a employee account");
					continue;
				}
				System.out.println("Username valid.");
				int count = 3;
				boolean valid = false;
				while (count > 0) {
					System.out.println("Please enter your password");
					String password = cons.nextLine();
					if (password.equals(emp.getPassword())) {
						valid = true;
						break;
					}
					System.out.println("Invalid password, please re-enter (" + count + " attempt(s) remaining)");
					count--;
				}
				if (valid) {
					showAccountActionMenu(emp, null);
					return;
				}
			}
		}
		System.out.println("No employee accounts found.  Please create one.");
		createEmployeeAccount();
	}

	// used to log in as administrator
	public void doAdminLogin() {
		Administrator emp = placeHolderAdmin;
		System.out.println("Checking admin accounts...");
		if (checkForAnyAdministrator()) {
			System.out.println("Found administrator account.");
			while (true) {
				System.out.println("Please enter your username.");
				String username = cons.next();
				emp = (Administrator) findAnEmployee(username);
				if (!username.contains("admin") || !username.equals(emp.getUsername())) {
					System.out.println(
							"Invalid username, please enter a valid admin account (must contain the word admin in it)");
					continue;
				}
				System.out.println("Username accepted.");
				int count = 3;
				boolean valid = false;
				while (count > 0) {
					System.out.println("Please enter your password");
					String password = cons.nextLine();
					if (password.equals(emp.getPassword())) {
						valid = true;
						break;
					}
					System.out.println("Invalid password, please re-enter (" + count + " attempt(s) remaining)");
					count--;
				}
				if (valid) {
					showAccountActionMenu(emp, null);
					return;
				} else {
					System.out.println("Failed to log in.  Returning to menu...");
					return;
				}
			}
		}
		System.out.println("No administrator accounts found.  Please create one.");
		createAdministratorAccount();
	}

	// helper methods to shorten some checks
	public boolean verifyCustomer(User user) {
		return user.getClass().equals(Customer.class);
	}

	public boolean verifyAdministrator(User user) {
		return user.getClass().equals(Administrator.class);
	}

	public boolean verifyEmployee(User user) {
		return user.getClass().equals(Employee.class);
	}

	public boolean verifyJointAccount(Account a) {
		return a.getClass().equals(JointAccount.class);
	}
	
	
	/* 
	 * ***********************************************
	 * 	All Code below this is for testing purposes***
	 * ***********************************************
	 */
	
	 /*
	  *  This method is for testing 
	 * 	Adding 400 dollars to a customer Account
	 */
	
	/*
	 *  deposits an amount to an Account
	 *  adding 400 to user u account 
	 */
	
		public double testdoDeposit(User u, Account a) {
			boolean valid = false;
			while (!valid) {
				try {
					System.out.println("Please enter the amount you wish to deposit");
					double amount = 400;
					if (amount < 0.01) {
						System.out.println("Cannot deposit less than one cent.");
					} else {
						a.setBalance(a.getBalance() + amount);
						System.out.println(
								"Successfully deposited $" + amount + " to account.  New balance is $" + a.getBalance());
						valid = true;
						log.info(new Transaction('d', a, a, a.getBalance() + amount, amount, u).toString());
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			return a.getBalance();
		}

		/*
		 *  withdraw an amount from an account
		 *  withdraw 1 from account
		 */
		public double testdoWithdrawal(User u, Account a) {
			boolean valid = false;
			while (!valid) {
				try {
					System.out.println("Please enter the amount you wish to withdraw");
					double amount = 1;
					if (amount < 0.01) {
						System.out.println("Cannot withdraw less than one cent.");
					} else if (amount > a.getBalance()) {
						System.out.println("Cannot overdraw an account.");
					} else {
						valid = true;
						a.setBalance(a.getBalance() - amount);
						System.out.println(
								"Successfully withdrew $" + amount + " to account.  New balance is $" + a.getBalance());
						log.info(new Transaction('t', a, a, a.getBalance() + amount, amount, u).toString());
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			return a.getBalance();
		}

		// transfer balances between accounts
		public double testdoTransfer(User u, Account a) {
			boolean valid = false;
			while (!valid) {
				try {
					System.out.println("Please enter the username of the account you wish to deposit to");
					String username = "rneslson";
						
					if(username.isEmpty()) {
					System.out.println("Please enter a valid username");
					continue;
					}
						
					Customer c = findACustomer(username);	
					if(c != null) {
						Account ac = findAnAccount(c);
						
						System.out.println("Please enter the amount you wish to transfer");
						double amount = 200;
							
						if (amount < 0.01) {
							System.out.println("Cannot deposit less than one cent.");
						} else {
							ac.setBalance(ac.getBalance() + amount);
							a.setBalance(a.getBalance() - amount);
							System.out.println(
									"Successfully transfered $" + amount + " to "+ username);
							//log.info(new Transaction('t', a, ac, a.getBalance() + amount, amount, u).toString());
							valid = true;
						}
							
					}
					else {
						System.out.println("Please enter a valid username");
						continue;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			}
			return a.getBalance();
		}
	
	public Account testcreateNewBankAccount(Customer c, int choice) {
		if (Account.pendingAccounts.size() + Account.activeAccounts.size() > 1) {
			boolean valid = false;
			while (!valid) {
				System.out.println("Would you like to create a single owner account or a joint account?");
				System.out.println("Enter 1 for single account, enter 2 for joint account.");
				try {

					switch (choice) {
					case 1:
						valid = true;
						System.out.println("Creating new bank account...");
						return new Account(c);

					case 2:
						valid = true;
						System.out.println("Creating new joint bank account...");
						return testcreateJointAccount(c);
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid selection");
				}
			}

		}
		return new Account(c);
	}
	
	// create a new customer joint account
	// Creating a joint account with c2
		public JointAccount testcreateJointAccount(Customer c1) {
			Customer c2 = null;
			int attemptsRemaining = 3;
			while (attemptsRemaining > 0) {
				System.out.println("Please enter the username of the second account holder (" + attemptsRemaining
						+ " attempt(s) remaining)");
				String user = "dnold";
				try {
					c2 = findACustomer(user);
					if (c2 == null) {
						attemptsRemaining--;
					} else {
						attemptsRemaining=0;
						break;
					}
				} catch (NullPointerException e) {
					System.out.println("Invalid account entered.");
					attemptsRemaining--;
				}
			}
			try {
				if (c2 == null) {
					System.out.println("Failed to create joint account.");
					return null;
				}
			} catch (NullPointerException e) {
				System.out.println("Failed to create joint account.");
			}
			return new JointAccount(c1, c2);
		}
}
