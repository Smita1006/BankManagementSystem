package controller;

public class Account {
        /// properties
        private String Name;
        private String accountNumber;
        private String password;
        private double balance;
      
        ///constructor
        public Account(String Name, String accountNumber, String password){

            this.Name = Name;
            this.accountNumber = accountNumber;
            this.password = password;
            this.balance = 0.0;

        }

        ///methods
        public boolean validatePassword(String inputPassword){
            return this.password.equals(inputPassword);  ///checks if the password entered by the user during login matches with the stored password set at the time of account creation.
        }

        public boolean validateName(String inputName){
            return this.Name.equals(inputName);
        }

        public void deposit(double amount){
            if(amount > 0 ){
                balance += amount;

                System.out.println("Rs." + amount + " deposited successfully.");
            }
        }

        public boolean withdraw(double amount){
            if(amount > 0 && amount <= balance){
                balance -= amount;

                System.out.println("Rs. " + amount + " withdrawn successfully.");
                return true;
            }
            else{
                System.out.println("Insufficient balance or invalid amount!!!");
                return false;
            }
        }

        public double getBalance(){
            return balance;
        }

        public String getAccountNumber(){
            return accountNumber;

        }

        public String getName(){
            return Name;
        }






    }
    

