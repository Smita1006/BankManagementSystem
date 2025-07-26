package controller;
import java.util.*;


public class Bank {

    private List<Account> accounts = new ArrayList<>();
    HashMap<String, Account> accountMap = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public List<Account> getAccounts() {
        return accounts;
    }

    private static final String BANK_CODE = "256";
    private static final String BRANCH_CODE = "05";
    private static int account_Sequence = 1;

    public String generate_accNumber(){
        String sequencePart = String.format("%06d",account_Sequence++);

        return BANK_CODE + BRANCH_CODE + sequencePart;

    }

    public Account createAccount(String name, String password) {
    String accountNumber = generate_accNumber();
    Account newAccount = new Account(name, accountNumber, password);
    accounts.add(newAccount);
    accountMap.put(accountNumber, newAccount);
    return newAccount;
}


    public Account login() {
        System.out.print("Enter Account number : ");
        String accNum = sc.nextLine();

        System.out.print("Enter your password : ");
        String password = sc.nextLine();

        for(Account acc : accounts){
            if(acc.getAccountNumber().equals(accNum) && acc.validatePassword(password)){
                return acc;

            }

        }

        System.out.println("Invalid Credentials. TRY AGAIN!!!");
        return null;


    }


    public boolean transferFunds(String fromAccNo, String toAccNo, double amount){

        Account sender = accountMap.get(fromAccNo);
        Account reciever = accountMap.get(toAccNo);

        if(sender == null || reciever == null){
            System.out.println("one or both account numbers are invalid!!!");
            return false;
        }

        if(sender.withdraw(amount)){
            reciever.deposit(amount);
            System.out.println("TRANSFER SUCCESSFULL!!." + " Rs. " + amount + " transferred from " + fromAccNo + " to " + toAccNo );
            return true;
        }
        else{
            System.out.println("Insufficient balance in sender's account. ");
            return false;
        }


    }

    public void userMenu(Account account, Bank bank){
        int choice = -1;
        System.out.println("WELCOME " + account.getName() + " TO JAVA BANK SYSTEM!!!");
        do{
            
            System.out.println("***** Account Menu *****");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.check balance");
            System.out.println("4.Transfer Funds");
            System.out.println("5.logout");

        System.out.println("Enter your choice : ");
        choice = sc.nextInt();

        switch(choice){

            case 1:
            System.out.print("Enter the deposit amount : ");
            double dep = sc.nextDouble();
            account.deposit(dep);
            break;


            case 2:
            System.out.print("Enter the withdraw amount : ");
            double with = sc.nextInt();
            account.withdraw(with);
            break;

            case 3:
            System.out.println("current balance : " + "Rs. "  + account.getBalance());
            break;

            case 4:
            sc.nextLine();
            System.out.print("Enter reciever's Account Number : ");
            String recieverAccNo = sc.nextLine();

            System.out.print("Enter the transfer amount : ");
            double transferAmount = sc.nextDouble();

            boolean success = bank.transferFunds(account.getAccountNumber(), recieverAccNo, transferAmount);
            if(success){
                System.out.println("Transfer Successfull!!");
                
            }
            else{
                System.out.println("Transfer failed!!!");
            }
            
            break;

            default:
            System.out.print("Invalid option.");

        }

        } while(choice!=5);
            sc.nextLine();

        }

       
           
        

    }


