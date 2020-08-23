package bankingapp.BankingApplication;

import java.util.ArrayList;

public class JointAccount extends Account{
	Customer member1;
	Customer member2;
	
	public static ArrayList<JointAccount> activeJointAccounts = new ArrayList<JointAccount>();
	
	public JointAccount(Customer member1,Customer member2, int accountId) {
		super(member1 ,accountId);	//member1 is the primary account holder
		this.member1 = member1;
		this.member2 = member2;
		this.setBalance(0.0);
		activeJointAccounts.add(this);
	}
		
	public String toString() {
		return member1.getFirstName() + " " + member1.getLastName() + " " + member2.getFirstName() +" "
				+ member2.getLastName() + " " + this.getAccountId() + "  " + this.getBalance(); 
	}
	
	public void showAccountInfo() {
		System.out.println(this.toString());
	}
	
}
