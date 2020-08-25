package bankingapp.BankingApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Test","Name", "user", "pass");
	Customer c2 = new Customer("Test","Name2", "user2", "pass");
	Customer c3 = new Customer("Test", "Name3", "user3", "pass");
	
	Account a = new Account(c);
	Account a2 = new Account(c2);
	
	
	Employee e = new Employee("TestEm");
	Administrator ad = new Administrator("TestAdm", 1234);
	Transaction t = new Transaction('w',a, a, 100.0, 12.0);
	App ap = new App();
	
	
	@Test
	void testNewBalance() {
		Assertions.assertEquals(400, ap.TestdoDeposit(a));
	}
	
	/*
	 * Testing that the customer account
	 * was updated after a deposit
	 */
	@Test
	void testGetBalance() {
		ap.TestdoDeposit(a);
		Assertions.assertEquals(400, a.getBalance());
	}
	
	/*
	 * Deposit money then withdraw a portion of it
	 */
	@Test 
	void testdoWithdrawl() {
		ap.TestdoDeposit(a);
		Assertions.assertEquals(399.0, ap.TestdoWithdrawal(a));
	}
	
	/*
	 *	This test is disabled since I dont understand how 
	 *	the doTransfer function works
	 */
	@Disabled
	void TestdoTransfer() {
		
	}

	@Test
	void testAccountOwner() {
		Assertions.assertEquals("Test Name", c.getFirstName() + " " + c.getLastName());
	}
	
	/*
	 * Create customer account through 
	 * the createNewbanckAccount
	*/
	@Test
	void TestCreateNewBankAccount() {
		Assertions.assertNotEquals(null, ap.testcreateNewBankAccount(c3, 1));
	}
	
	@Test
	void TestCreateNewBankAccountPendingArray() {
		Account ac = ap.testcreateNewBankAccount(c3, 1);
		Assertions.assertEquals(3,ac.getPendingAccounts().size() );
	}
	
	/*
	 * the function create newBankAccout in app class
	 * calls on createJointAccount which is what is being tested
	 */	
	@Test
	void TestCreateNewJointAccount() {
		Assertions.assertNotEquals(null, ap.testcreateNewBankAccount(c3, 2));
	}
	
	 
	@Test
	void TestCreateNewJointAccountPendingArray() {
		Account jtc = ap.TestcreateJointAccount(c3);
		Assertions.assertEquals(3,jtc.getPendingAccounts().size());
	}
	
	@Test
	void testFindAccount() {
		Account a3 = new Account(c3);
		Assertions.assertEquals(a3, ap.findAnAccount(c3));
	}
	
	@Test
	void testFindCustomers() {
		Assertions.assertEquals(c2, ap.findACustomer("user2"));
	}
	
	@Test
	void testFindAnEmployee() {
		Employee e2 = new Employee("TestEm2");
		Assertions.assertEquals(e2, ap.findAnEmployee("TestE2m"));
	}
	
	@Test
	void testCheckdAdministrator() {
		Assertions.assertEquals(true, ap.checkForAnyAdministrator());
	}

}
