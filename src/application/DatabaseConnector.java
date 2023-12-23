package application;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

public class DatabaseConnector {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseConnector() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("AnimalShelterDB");
    }

    public MongoCollection<Document> getAnimalsCollection() {
        return database.getCollection("Animals");
    }

    public MongoCollection<Document> getUsersCollection() {
        return database.getCollection("Users");
    }

    public void addUser(Document user) {
        MongoCollection<Document> usersCollection = getUsersCollection();
        usersCollection.insertOne(user);
    }



    public boolean validateUser(String email, String password) {
        MongoClient mongoClient = MongoClients.create(); 
        MongoDatabase database = mongoClient.getDatabase("AnimalShelterDB"); 
        MongoCollection<Document> users = database.getCollection("Users");

        Document foundUser = users.find(eq("email", email)).first();
        if (foundUser != null) {
            String storedPassword = foundUser.getString("password");
            return storedPassword.equals(password); 
        }
        return false; 
    }

    public User getUserByEmail(String email) {
        MongoCollection<Document> usersCollection = getUsersCollection();
        Document doc = usersCollection.find(eq("email", email)).first();

        if (doc != null) {
            return new User(
                doc.getString("firstName"),
                doc.getString("lastName"),
                doc.getString("email"),
                doc.getString("username"),
                doc.getString("password"),
                doc.getString("role")
            );
        }
        return null; 
    }
    
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
