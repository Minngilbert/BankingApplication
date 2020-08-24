package bankingapp.BankingApplication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

	Customer c = new Customer("Test","Name", "user", "pass");
	Customer c2 = new Customer("Test","Name2", "user2", "pass");
	Account a = new Account(c,123);
	Employee e = new Employee("TestEm", 123);
	Administrator ad = new Administrator("TestAdm", 1234);
	Transaction t = new Transaction('w',a, a, 100.0, 12.0);
	

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
