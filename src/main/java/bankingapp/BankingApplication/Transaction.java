package bankingapp.BankingApplication;

public class Transaction {

	private char type;
	private double accountPreBalance;
	private double amountTaken;
	private Account accountPerformed;
	private Account accountAffected;
	
	public Transaction(char t, Account accountperformed, Account accountaffected, double accountprebalance, double amounttaken) {
		type = t;
		if(t != 't') accountaffected = accountperformed;
		accountPerformed = accountperformed;
		accountAffected = accountaffected;
		accountPreBalance = accountprebalance;
		amountTaken = amounttaken;
	}
	
	private String getType(char c) {
		switch(c) {
		case 'w':
			return "Widthdrawal";

		case 'd':
			return "Deposit";

		case 't':
			return "Transfer";

		}
		return "Unknown";
	}
	
	public String toString() {
		return String.format("Transaction type: %s%nAccount ID Inititating action: %d%n" +
				"Account ID Affected by action: %d%nBalance before action occurred: $%.2f%n" +
				"Amount Taken: $%.2fAmount Post Action: $%.2f%n", 
				getType(type), accountPerformed.getAccountId(), accountAffected.getAccountId(), 
				accountPreBalance,amountTaken,accountAffected.getBalance()
				);
	}
	
	
}
