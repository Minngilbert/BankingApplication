package bankingapp.BankingApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Test","Name");
	Customer c2 = new Customer("Test","Name2");
	Account a = new Account(1,0.0,true);
	Employee e = new Employee();
	Administrator ad = new Administrator();
	Transaction t = new Transaction();
	
	
	
	@Test
	void testGetBalance() {
		Assertions.assertEquals(0.0, a.getBalance());
	}
	
	
	
	@Test
	void testNewBalance() {
		Assertions.assertNotEquals(255.0, a.getBalance());
	}
	@Test
	void testAccountOwner() {
		Assertions.assertEquals("Test Name", a.getOwner());
	}
	@Test
	void testExistsAdministrator() {
		Assertions.assertNotEquals(null, a);
	}
	
	@Test
	void testDeposit() {
		//Assertions.assertEquals(300.0, a.deposit(45.0));
	}
}
