package bankingapp.BankingApplication;

public interface AccountManipulator {
	public double deposit(Account account, double amount);
	public double withdraw(Account account,double amount);
	public boolean transfer(Account fromAcc, Account toAcc, double amount);
}
