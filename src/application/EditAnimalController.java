package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class EditAnimalController {
    @FXML private TextField nameField;
    @FXML private TextField speciesField;
    @FXML private TextField ageField;
    @FXML private TextField statusField;
    @FXML private Button saveButton;

    private DatabaseConnector dbConnector;
    private String animalId; 

    public EditAnimalController() {
        dbConnector = new DatabaseConnector(); 
    }

    public void setAnimalData(String animalId, String name, String species, String age, String status) {
        this.animalId = animalId;
        nameField.setText(name);
        speciesField.setText(species);
        ageField.setText(age);
        statusField.setText(status);
    }

    @FXML
    private void handleSave(ActionEvent event) {
        String updatedName = nameField.getText();
        String updatedSpecies = speciesField.getText();
        String updatedAge = ageField.getText();
        String updatedStatus = statusField.getText();

        
        MongoCollection<Document> collection = dbConnector.getAnimalsCollection();
        Bson update = combine(
            set("name", updatedName),
            set("species", updatedSpecies),
            set("age", updatedAge),
            set("status", updatedStatus)
        );
        collection.updateOne(eq("_id", new ObjectId(animalId)), update);

      
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("Edit Animal");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Animal information updated successfully.");
        confirmationAlert.showAndWait();

       
        saveButton.getScene().getWindow().hide();
    }
}
