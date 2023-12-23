package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty species;
    private final StringProperty age;

    public Animal(String id, String name, String species,  String age) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.species = new SimpleStringProperty(species);
        this.age = new SimpleStringProperty(age);
    }

    // Getters and setters
    public String getId() { return id.get(); }
    public void setId(String id) { this.id.set(id); }
    public StringProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public String getSpecies() { return species.get(); }
    public void setSpecies(String species) { this.species.set(species); }
    public StringProperty speciesProperty() { return species; }

    public String getAge() { return age.get(); }
    public void setAge(String age) { this.age.set(age); }
    public StringProperty ageProperty() { return age; }
}
