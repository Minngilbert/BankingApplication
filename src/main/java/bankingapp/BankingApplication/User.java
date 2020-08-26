package bankingapp.BankingApplication;

public abstract class User {
	private String username;
	private String password;
	public abstract String getUsername();
	public abstract String getPassword();
	public abstract void setUsername(String user);
	public abstract void setPassword(String pass);
	//public abstract void setPassword(String pass);
}