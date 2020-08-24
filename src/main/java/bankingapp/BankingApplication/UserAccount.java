package bankingapp.BankingApplication;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAccount implements Serializable {
	private User user;
	private String username;
	private String password;
	
	public static ArrayList<UserAccount> userList = new ArrayList<UserAccount>();
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public UserAccount(User u, String user, String pass) {
		this.user = u;
		username = user;
		password = pass;
		userList.add(this);
	}
	
	public boolean matchCustomer(User u) {
		return user.getUsername().equals(u.getUsername());
	}
}
