package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.io.IOException;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

     private Account loggedInAccount; 

    private Bank bank;

public void setBank(Bank bank) {
    this.bank = bank;
}

    @FXML
private void handleGoToCreateAccount() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/ui/create_account.fxml"));
        Parent root = loader.load();

        CreateAccountController controller = loader.getController();
        controller.setBank(bank); // pass same bank instance

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Create Account");
        stage.show();

    } catch (Exception e) {
        e.printStackTrace();
    }
}



    @FXML
    private void handleLogin(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.isEmpty() || password.isEmpty()){
            loginMessageLabel.setText("Please enter both username and password.");
            return;
        }

        loggedInAccount = null;
        
        for (Account acc : bank.getAccounts()) {
        if (acc.getName().equals(username) && acc.validatePassword(password)) {
            loggedInAccount = acc;
            break;
        }
    }

        if (loggedInAccount != null) {
        System.out.println("Login successful!!!");
        loginMessageLabel.setText("Login successful!!!");

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to the Bank Management System!");
        alert.showAndWait();

        usernameField.clear();
        passwordField.clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/ui/menu.fxml"));
            Parent root = loader.load();

            MenuController menuController = loader.getController();
            menuController.setBank(bank);
            menuController.setLoggedInAccount(loggedInAccount);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main Menu");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            loginMessageLabel.setText("Error loading main menu.");
        }
    } else {
        System.out.println("Invalid username or password!!");
        loginMessageLabel.setText("Invalid username or password!!");
        passwordField.clear();
    }
}

        


    }
    

