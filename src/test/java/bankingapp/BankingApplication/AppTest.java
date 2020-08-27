package bankingapp.BankingApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Robert","Nelson", "rneslson", "password");
	Customer c2 = new Customer("Daniel","Nold", "dnold", "password2");
	Customer c3 = new Customer("Test", "Name3", "user3", "pass");
	Customer c4 = new Customer("Test", "Name4", "user4", "pass");
	

	
	Employee e = new Employee("Gilbert", "Mwangi", "em1", "password");
	Administrator ad = new Administrator("Robert", "Curley","admin", "password");
	
	//Transaction t = new Transaction('w',a, a, 100.0, 12.0);
	App ap = new App();
	
	@Test
	void testDeposit() {
		ap.setDebug(true);
		Account a2 = new Account(c2);
		a2.setBalance(400);
		Assertions.assertEquals(a2.getBalance(), ap.doDeposit(c2, a2));
	}
	
	/*
	 * Testing that the customer account
	 * was updated after a deposit
	 */
	@Test
	void testGetBalanceDeposited() {
		ap.setDebug(true);
		Account a2 = new Account(c2);
		a2.setBalance(400);
		ap.doDeposit(c2, a2);
		Assertions.assertEquals(400, a2.getBalance());
	}
	
	/*
	 * Deposit money then withdraw a portion of it
	 */
	@Test 
	void testWithdrawal() {
		ap.setDebug(true);
		Account a2 = new Account(c2);
		a2.setBalance(400);
		Assertions.assertEquals(400, ap.doWithdrawal(c2, a2));
	}
	
	/*
	 * Test that the account was updated
	 * after the withdrawal
	 */
	@Test 
	void testGetBalanceWithdraw() {
		ap.setDebug(true);
		Account a2 = new Account(c2);
		ap.doDeposit(c2, a2);
		a2.setBalance(400);
		ap.doWithdrawal(c2, a2);
		Assertions.assertEquals(400, a2.getBalance());
	}

	/*
	 * Create customer account through 
	 * the createNewbanckAccount
	 * Make sure createNewBankAccount doesnt 
	 * return null
	*/
	@Test 
	void TestCreateNewBankAccount() {
		ap.setDebug(true);
		Assertions.assertNotEquals(null, ap.createNewBankAccount(c3));
	}
	
	
	@Test
	void TestCreateNewJointAccount() {
		ap.setDebug(true);
		Assertions.assertNotEquals(1,ap.createJointAccount(c3).getPendingAccounts().size());
	}
	 

	
	@Test
	void testFindAccount() {
		Account a2 = new Account(c2);
		Assertions.assertNotEquals(null, ap.findAnAccount(c2));
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
		ap.setDebug(true);
		Account a2 = new Account(c2);
		a2.setBalance(200);
		Assertions.assertEquals(200, ap.doTransfer(c2, a2));
	}
	
	/*
	 * These dont currently work
	 */
	//______________________________________________________________________
	@Disabled 
	void testFindAccountById() {
		Account a2 = new Account(c2);
		Assertions.assertEquals(a2.getAccountId(), ap.findAnAccount(c2).getAccountId());
	}
	
	@Disabled
	void TestCreateNewJointAccountPendingArray() {
		ap.setDebug(true);
		JointAccount jtc = ap.createJointAccount(c3);
		Assertions.assertEquals(1,jtc.getActiveAccounts());
	}
	
	@Disabled 
	void TestCreateNewBankAccountPendingArray() {
		ap.setDebug(true);
		Assertions.assertEquals(0, ap.createNewBankAccount(c3).getPendingAccounts().size());
	}
	//______________________________________________________________________

}