package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Font.loadFont(getClass().getResourceAsStream("resources/Roboto-Medium.ttf"), 12);
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("resources/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            // MongoDB Connection Test
            testMongoDBConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void testMongoDBConnection() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("testDatabase"); // Replace "testDatabase" with your database name
            System.out.println("Connected to MongoDB database: " + database.getName());
        } catch (Exception e) {
            System.out.println("Failed to connect to MongoDB");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
