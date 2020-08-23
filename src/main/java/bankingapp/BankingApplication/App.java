package bankingapp.BankingApplication;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee e1 = new Employee("Jack", 123);
        Administrator e2 = new Administrator("Mary", 1234);
        
        Customer c1 = new Customer("firstName","lastName","username", "password");
        c1.registerForAccount(123);
        
        
        Account account = new Account();
        System.out.println(account.getPendingAccounts().size());
        
        e1.getPendingAccounts();
        e1.updateAccount(123);
        System.out.println(account.getPendingAccounts().size());
        System.out.println(account.getActiveAccounts().get(0));
    }
}
