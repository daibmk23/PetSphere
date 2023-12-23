package application;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.event.ActionEvent;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private DatabaseConnector dbConnector = new DatabaseConnector();
    private SceneController sceneController = new SceneController(); 
    private SessionManagement sessionManagement = new SessionManagement();

    @FXML
    private void handleSignIn(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (validateCredentials(email, password)) {
            User currentUser = dbConnector.getUserByEmail(email); 
            SessionManagement.loginUser(user);
            sceneController.switchToDashboard(event);
        } else {
            showLoginError();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        sceneController.switchToRegistrationPage(event);
    }

    private boolean validateCredentials(String email, String password) {
        return dbConnector.validateUser(email, password);
    }

    private void showLoginError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Invalid Credentials");
        alert.setContentText("Please check your email and password.");
        alert.showAndWait();
    }
}
