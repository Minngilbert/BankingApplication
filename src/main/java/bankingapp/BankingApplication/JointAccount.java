package bankingapp.BankingApplication;

public class JointAccount extends Account{
	Customer member1;
	Customer member2;
	
	public JointAccount(Customer member1,Customer member2, int accountId, double balance, boolean pendingApproval) {
		super(accountId, pendingApproval);	
		this.member1 = member1;
		this.member2 = member2;
	}
}
