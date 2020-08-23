package bankingapp.BankingApplication;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee e1 = new Employee("Jack", 9092342);
        Administrator e2 = new Administrator("Mary", 1234);
        
        Customer c1 = new Customer("firstName","lastName","username", "password");
        c1.registerForAccount(123);
        Customer c2 = new Customer("firstName2","lastName2","username2", "password2");
        
        Account account = new Account();
        System.out.println(account.getPendingAccounts().size());
        
        JointAccount join = new JointAccount(c1, c2, 890);
        
        for(Account acc: account.getPendingAccounts()) {
        	acc.showAccountInfo();
        }  
        
    }
}
