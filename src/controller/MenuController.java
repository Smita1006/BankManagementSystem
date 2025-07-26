package controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class MenuController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Button logoutButton;

     private Bank bank;
    private Account loggedInAccount;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

     public void setLoggedInAccount(Account account) {
        this.loggedInAccount = account;
        welcomeLabel.setText("Welcome, " + account.getName());
    }

    


     @FXML
    private void handleCheckBalance() {
        messageLabel.setText("Balance: ₹" + loggedInAccount.getBalance());
    }

    @FXML
    private void handleDeposit() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit");
        dialog.setHeaderText("Enter amount to deposit:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                loggedInAccount.deposit(amount);
                messageLabel.setText("Deposited: ₹" + amount);
            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid amount!");
            }
        });
    }

     @FXML
    private void handleWithdraw() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw");
        dialog.setHeaderText("Enter amount to withdraw:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                boolean success = loggedInAccount.withdraw(amount);
                if (success) {
                    messageLabel.setText("Withdrawn: ₹" + amount);
                } else {
                    messageLabel.setText("Insufficient balance!");
                }
            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid amount!");
            }
        });
    }

    @FXML
    private void handleTransfer() {
        TextInputDialog accDialog = new TextInputDialog();
        accDialog.setTitle("Transfer Funds");
        accDialog.setHeaderText("Enter recipient's account number:");

        Optional<String> accResult = accDialog.showAndWait();
        accResult.ifPresent(receiverAcc -> {
            TextInputDialog amtDialog = new TextInputDialog();
            amtDialog.setHeaderText("Enter amount to transfer:");

            Optional<String> amtResult = amtDialog.showAndWait();
            amtResult.ifPresent(amountStr -> {
                try {
                    double amount = Double.parseDouble(amountStr);
                    boolean success = bank.transferFunds(loggedInAccount.getAccountNumber(), receiverAcc, amount);
                    if (success) {
                        messageLabel.setText("Transferred ₹" + amount);
                    } else {
                        messageLabel.setText("Transfer failed.");
                    }
                } catch (NumberFormatException e) {
                    messageLabel.setText("Invalid amount!");
                }
            });
        });
    }



    @FXML
    private void handleLogout(){
        try{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/ui/login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setBank(bank);

      
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
}
