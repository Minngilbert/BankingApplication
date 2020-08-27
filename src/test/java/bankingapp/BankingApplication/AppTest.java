package bankingapp.BankingApplication;

import bankingapp.BankingApplication.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Robert","Nelson", "rneslson", "password");
	Customer c2 = new Customer("Daniel","Nold", "dnold", "password2");
	Customer c3 = new Customer("Test", "Name3", "user3", "pass");
	Customer c4 = new Customer("Test", "Name4", "user4", "pass");
	
	Account a = new Account(c);
	Account a2 = new Account(c2);
	
	Employee e = new Employee("Gilbert", "Mwangi", "em1", "password");
	Administrator ad = new Administrator("Robert", "Curley","admin", "password");
	
	//Transaction t = new Transaction('w',a, a, 100.0, 12.0);
	App ap = new App();
	
	@Test
	void testDeposit() {
		ap.setDebug(true);
		Assertions.assertEquals(400, ap.doDeposit(c2, a2));
	}
	
	/*
	 * Testing that the customer account
	 * was updated after a deposit
	 */
	@Test
	void testGetBalanceDeposited() {
		ap.doDeposit(c2, a2);
		Assertions.assertEquals(400, a2.getBalance());
	}
	
	/*
	 * Deposit money then withdraw a portion of it
	 */
	@Test 
	void testWithdrawal() {
		ap.doDeposit(c2, a2);
		Assertions.assertEquals(399.0, ap.doWithdrawal(c2, a2));
	}
	
	/*
	 * Test that the account was updated
	 * after the withdrawal
	 */
	@Test
	void testGetBalanceWithdraw() {
		ap.doDeposit(c2, a2);
		ap.doWithdrawal(c2, a2);
		Assertions.assertEquals(399, a2.getBalance());
	}

	/*
	 * Create customer account through 
	 * the createNewbanckAccount
	 * Make sure createNewBankAccount doesnt 
	 * return null
	*/
	@Test
	void TestCreateNewBankAccount() {
		Assertions.assertNotEquals(null, ap.createNewBankAccount(c3));
	}
	
	
	@Test
	void TestCreateNewBankAccountPendingArray() {
		Account ac = ap.createNewBankAccount(c3);
		Assertions.assertEquals(3,ac.getPendingAccounts().size() );
	}
	
	/*
	 * the function create newBankAccout in app class
	 * calls on createJointAccount which is what is being tested
	 */	
	@Test
	void TestCreateNewBankAccountJointAccount() {
		Assertions.assertNotEquals(null, ap.createNewBankAccount(c3));
	}
	
	@Test
	void TestCreateNewJointAccount() {
		JointAccount jtc = ap.createJointAccount(c3);
		Assertions.assertNotEquals(null,jtc.getActiveAccounts());
	}
	 
	@Test
	void TestCreateNewJointAccountPendingArray() {
		JointAccount jtc = ap.createJointAccount(c3);
		Assertions.assertNotEquals(3,jtc.getActiveAccounts());
	}
	
	@Test
	void testFindAccount() {
		Assertions.assertNotEquals(null, ap.findAnAccount(c2));
	}
	
	@Test
	void testFindAccountA() {
		Assertions.assertNotEquals(a2.getAccountId(), ap.findAnAccount(c2).getAccountId());
	}
	
	
	@Test
	void testFindCustomers() {
		Assertions.assertNotEquals(null, ap.findACustomer("dnold"));
	}
	
	@Test
	void testFindCustomersN() {
		Customer cnew = ap.findACustomer("dnold");
		Assertions.assertEquals("dnold", cnew.getUsername());
	}
	
	@Test
	void testFindAnEmployee() {
		Employee e2 = new Employee("Peter", "Nold", "EmUser", "password");
		Assertions.assertEquals(e2, ap.findAnEmployee("EmUser"));
	}
	
	@Test
	void checkForAnyAdministrator() {
		Assertions.assertEquals(true,ap.checkForAnyAdministrator());
	}
	
	@Test
	void testVerifyAdministrator() {
		Assertions.assertEquals(true, ap.verifyAdministrator(ad));
	}
	
	@Test
	void testverifyEmployee() {
		Assertions.assertEquals(true, ap.verifyEmployee(e));
	}
	
	@Test
	void testverifyCustomer() {
		Assertions.assertEquals(true, ap.verifyCustomer(c));
	}

	@Test
	void testVerifyAdministratorfalse() {
		Assertions.assertEquals(false, ap.verifyAdministrator(e));
	}
	
	@Test
	void testverifyEmployeefalse() {
		Assertions.assertEquals(false, ap.verifyEmployee(c));
	}
	
	
	@Test
	void testverifyCustomefalse() {
		Assertions.assertEquals(false, ap.verifyCustomer(ad));
	}

	@Test
	void TestdoTransfer() {
		ap.doDeposit(c2, a2);
		Assertions.assertEquals(200, ap.doTransfer(c2, a2));
	}
}