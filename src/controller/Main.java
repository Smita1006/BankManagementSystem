package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Bank bank = new Bank(); // shared bank instance used by all controllers

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller/ui/login.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setBank(bank); // inject the bank object

        primaryStage.setTitle("JavaFX Bank Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // launches JavaFX application
    }
}
