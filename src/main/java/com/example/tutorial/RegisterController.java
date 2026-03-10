package com.example.tutorial;

import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

public class RegisterController {
    @FXML
    private Button viewPassword;
    @FXML
    private TextField textField;
    @FXML
    private Label welcomeText;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    @FXML
    public void records(ActionEvent event) throws SQLException {
        com.example.tutorial.JdbcDao jdbcDao = new com.example.tutorial.JdbcDao();
        jdbcDao.retrieveRecords();

    }

    @FXML
    public void viewPassword(ActionEvent event) {
        if (passwordField.isVisible()) {
            textField.setText(passwordField.getText());
            textField.setVisible(true);
            textField.setManaged(true);

            passwordField.setVisible(false);
            passwordField.setManaged(false);
            viewPassword.setText("Hide");
        } else {
            passwordField.setText(textField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);

            textField.setVisible(false);
            textField.setManaged(false);
            viewPassword.setText("View");
        }
    }
    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (emailIdField.getText().isEmpty() ) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "please enter a password");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String fullName = fullNameField.getText();
        String emailId  = emailIdField.getText();
        String password = passwordField.getText();

        com.example.tutorial.JdbcDao jdbcDao = new com.example.tutorial.JdbcDao();
        jdbcDao.insertRecord(fullName, emailId,password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + fullNameField.getText());
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message ) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}