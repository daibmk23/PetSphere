package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class RegistrationController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private CheckBox userCheckBox;
    @FXML
    private CheckBox agencyCheckBox;

    private DatabaseConnector dbConnector = new DatabaseConnector();

    @FXML
    private void handleRegister(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String role = userCheckBox.isSelected() ? "User" : agencyCheckBox.isSelected() ? "Agency" : "";

        if (validateInputs(firstName, lastName, email, userName, password, confirmPassword, role)) {
            Document newUser = new Document("firstName", firstName)
                                    .append("lastName", lastName)
                                    .append("email", email)
                                    .append("userName", userName)
                                    .append("password", password) // Password should be hashed in a real application
                                    .append("role", role);
            dbConnector.addUser(newUser);
            showAlert("Registration Successful", "User registered successfully!", Alert.AlertType.INFORMATION);
        }
    }

        private boolean validateInputs(String firstName, String lastName, String email, String userName, String password, String confirmPassword, String role) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.isEmpty()) {
            return false;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            return false;
        }

        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        return !emailExists(email) && !usernameExists(userName);
    }

    private boolean emailExists(String email) {
        MongoCollection<Document> usersCollection = dbConnector.getUsersCollection();
        long count = usersCollection.countDocuments(Filters.eq("email", email));
        return count > 0;
    }

    private boolean usernameExists(String userName) {
        MongoCollection<Document> usersCollection = dbConnector.getUsersCollection();
        long count = usersCollection.countDocuments(Filters.eq("username", userName));
        return count > 0;
    }

    
    

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
