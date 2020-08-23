package bankingapp.BankingApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Test","Name");
	Customer c2 = new Customer("Test","Name2");
	Account a = new Account(1,255.0,true);
	Employee e = new Employee("TestEm", 123);
	Administrator ad = new Administrator("TestAdm", 1234);
	Transaction t = new Transaction();
	

	private void doDeposit() {
		a.setBalance(a.getBalance() + 45);
	}
	
	@Test
	void testGetBalance() {
		Assertions.assertEquals(255.0, a.getBalance());
	}
	
	
	@Test
	void testNewBalance() {
		doDeposit();
		Assertions.assertEquals(300.0, a.getBalance());
	}
	@Test
	void testAccountOwner() {
		Assertions.assertEquals("Test Name", c.getFirstName() + " " + c.getLastName());
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
