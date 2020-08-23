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
        
        
        /*
         * testing to see that the pending accounts array list gets updated
         * and that an account can be updated
         */
        e1.getPendingAccounts();
        e1.updateAccount(123);
        System.out.println(account.getPendingAccounts().size());
        System.out.println();
        
        /*
         * Testing to see that the active account array is updated
         */
        System.out.println(account.getActiveAccounts().get(0).getBalance());    
        System.out.println(c1.getAccountBalance());
        
        Account ac1 = c1.getCustomerAccount();
        
        //add money to customer account
        System.out.println("Just deposited some money " + c1.deposit(ac1, 1000));
        
        System.out.println(account.getActiveAccounts().get(0).getBalance());    
        System.out.println(c1.getAccountBalance());
        
        System.out.println("Just pulled some money out " +c1.toString()+ c1.withdraw(ac1, 501));
        
        System.out.println(account.getActiveAccounts().get(0).getBalance());    
        System.out.println(c1.getAccountBalance());
        
        
        
    }
}
