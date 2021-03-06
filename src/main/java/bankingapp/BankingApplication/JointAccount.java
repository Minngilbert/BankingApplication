package bankingapp.BankingApplication;

import java.io.Serializable;

public class JointAccount extends Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Customer member1;
	Customer member2;

	public JointAccount(Customer member1, Customer member2) {
		super(member1); // member1 is the primary account holder
		this.member1 = member1;
		this.member2 = member2;
		this.setBalance(0.0);
	}

	public String toString() {
		return "ID: " + this.getAccountId() + " Primary Account Owner: " + member1.getFirstName() + " " + 
				member1.getLastName() + " Secondary Account Owner: " + member2.getFirstName()
				+ " " + member2.getLastName() + " Balance: $" + this.getBalance();
	}

	public void showAccountInfo() {
		System.out.println(this.toString());
	}

}
