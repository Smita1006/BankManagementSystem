package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class CreateAccountController {
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private Bank bank;

    public void setBank(Bank bank){
        this.bank = bank;


    }

    @FXML
    private void handleCreateAccount(){
        String name = nameField.getText();
        String password = passwordField.getText();

        if(name.isEmpty() || password.isEmpty()){
            messageLabel.setText("Please enter name and password.");
            return;
        }

        Account newAccount = bank.createAccount(name, password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account Created");
        alert.setHeaderText(null);
        alert.setContentText("Your account number is: " + newAccount.getAccountNumber());
        alert.showAndWait();


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/ui/login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setBank(bank); // pass the same bank object

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



    

    

