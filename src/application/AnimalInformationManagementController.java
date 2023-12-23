package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.io.IOException;

public class AnimalInformationManagementController {
    private DatabaseConnector dbConnector = new DatabaseConnector();

    @FXML private VBox animalListVBox;
    @FXML private TextField searchBar;
    @FXML private ComboBox<String> typeFilter;
    @FXML private ComboBox<String> ageFilter;
    @FXML private ComboBox<String> statusFilter;
    @FXML private Button addNewAnimalButton;
    @FXML private Button applyFiltersButton;

    public void initialize() {
        setupFilters();
        loadAnimals(null);
    }

    private void setupFilters() {
        // Setup your filter items here
        typeFilter.getItems().addAll("Dog", "Cat", "Bird", "Reptile", "Other");
        ageFilter.getItems().addAll("Puppy", "Young", "Adult", "Senior");
        statusFilter.getItems().addAll("Healthy", "Special Needs", "In Treatment", "Adopted");
    }

    private void loadAnimals(Document filter) {
        animalListVBox.getChildren().clear();
        MongoCollection<Document> collection = dbConnector.getAnimalsCollection();
        for (Document doc : (filter == null ? collection.find() : collection.find(filter))) {
            String id = doc.getObjectId("_id").toString();
            String name = doc.getString("name");
            String species = doc.getString("species");
            String age = doc.getString("age");
            String status = doc.getString("status");
            animalListVBox.getChildren().add(createAnimalEntry(id, name, species, age, status));
        }
    }

    private HBox createAnimalEntry(String id, String name, String species, String age, String status) {
        ImageView imageView = new ImageView(); // Set image as required
        Label nameLabel = new Label("Name: " + name);
        Label speciesLabel = new Label("Species: " + species);
        Label ageLabel = new Label("Age: " + age);
        Label statusLabel = new Label("Status: " + status);
        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> openEditAnimalWindow(id, name, species, age, status));
        Button moreInfoButton = new Button("More Info");
        moreInfoButton.setOnAction(e -> showAnimalDetails(id, name, species, age, status));

        HBox entryBox = new HBox(imageView, nameLabel, speciesLabel, ageLabel, statusLabel, editButton, moreInfoButton);
        entryBox.setSpacing(10);
        return entryBox;
    }

    private void openEditAnimalWindow(String id, String name, String species, String age, String status) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAnimal.fxml"));
            Parent root = loader.load();
            EditAnimalController controller = loader.getController();
            controller.setAnimalData(id, name, species, age, status);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAnimalDetails(String id, String name, String species, String age, String status) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Animal Details");
        infoAlert.setHeaderText("Details for " + name);
        String content = "ID: " + id + "\nName: " + name + "\nSpecies: " + species + "\nAge: " + age + "\nStatus: " + status;
        infoAlert.setContentText(content);
        infoAlert.showAndWait();
    }

    @FXML
    private void handleAddNewAnimal(ActionEvent event) {
        // Logic for adding a new animal
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAnimal.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onApplyFilters(ActionEvent event) {
        Document filter = new Document();
        if (!searchBar.getText().isEmpty()) {
            filter.append("name", searchBar.getText());
        }
        if (typeFilter.getValue() != null) {
            filter.append("type", typeFilter.getValue());
        }
        if (ageFilter.getValue() != null) {
            filter.append("age", ageFilter.getValue());
        }
        if (statusFilter.getValue() != null) {
            filter.append("status", statusFilter.getValue());
        }
        loadAnimals(filter);
    }
}
