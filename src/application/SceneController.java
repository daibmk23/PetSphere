package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.*;

public class SceneController{
	private Stage stage;
	private Scene scene;
	private Parent root; 
	@FXML
	private TextField staffText, taskText, eventName, timeField, dateField, confirmPassword, email, password, userName,firstName,lastName,userEmail, addName, addVacc, addTr, addHis, addAller; 
	@FXML
	private RadioButton user, agency;
	@FXML
	private PasswordField userPassword;
	
	@FXML
	public Label adoptReq, setEmail, setPassword, settName, staff1, staff2, task1, task2, event1, date1, time1, event2, date2, time2, vaccine, history, allergies, tr, refundlb1, refundlb2, refundlb3, refundlb4, donations, refunds;
	
	@FXML
	public Button button1, button2;
	
	private String cPass,mail,Uname,pass;
	private ArrayList<String> users = new ArrayList<>();
	private ArrayList<String> emails = new ArrayList<>();
	private ArrayList<String> passwords = new ArrayList<>();
	private int c = 0, d = 0, x = 0, count = 0, f = 0;

	
	public SceneController() throws IOException {
		addUserToLists();
		addEmailToLists();
		addPasswordsToLists();
		
	}
	
	public void sendRequest(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to Send Form?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setContentText("Request Sent Succesfully");
			alert2.showAndWait();
		}
	}
	public void sendAdopt(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to Send Adoption Request?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setContentText("Request Sent Succesfully");
			alert2.showAndWait();
		}
	}
	
	public void changeName() throws IOException {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Please Enter New Name");
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) 
		{
			int count = 0;
			for(int i = 0; i<users.size();i++) 
			{
				if(users.get(i).equals(Uname)) 
				{
					count = i+1;
					break;
				}
				System.out.println(Uname);
			}
		    String userInput = result.get();
		    modifyFile(count,1,userInput);
		    settName.setText(userInput);
		    
		} 	
		
	}
	
	public void changeEmail() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Please Enter New Email");
		Optional<String> result = dialog.showAndWait();
		String userInput = result.get();
	    setEmail.setText(userInput);
	}
	public void setPassword() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Please Enter New Password");
		Optional<String> result = dialog.showAndWait();
		String userInput = result.get();
		setPassword.setText(userInput);
	}
	
	public void approveReq() {
		adoptReq.setText("Isa Khan adopted Mr. Pickles");
	}
	public void addStaff() {
		if (d==0)
		{
			staff1.setText(staffText.getText());
		}
		if (d ==1 )
		{
			staff2.setText(staffText.getText());
		}
		d++;
	}
	
	public void addTask() {
		if (x==0)
		{
			task1.setText(taskText.getText());
		}
		if (x ==1 )
		{
			task2.setText(taskText.getText());
		}
		x++;
	}
		
	public void refund(ActionEvent e) {
		refundlb1.setText("$0");
		donations.setText("$300");
		refunds.setText("$50");
	}
	public void refunds2(ActionEvent e) {
		refundlb2.setText("$0");
		donations.setText("$250");
		refunds.setText("$100");
	}
	public void refunds3(ActionEvent e) {
		refundlb3.setText("$0");
		donations.setText("$100");
		refunds.setText("$250");
	}
	public void refunds4(ActionEvent e) {
		refundlb4.setText("$0");
		donations.setText("$0");
		refunds.setText("$350");
	}

	public void switchScene1(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	
	public void switchScene2(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
		stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void text() {
		if ( f == 0) {
		button1.setText(addName.getText());
		vaccine.setText(addVacc.getText());
		history.setText(addHis.getText());
		allergies.setText(addAller.getText());
		tr.setText(addTr.getText());
		button1.setStyle("-fx-background-color: #BEBEBE;");
		button1.setTextFill(Color.BLACK);}
	}
	public void jerry() {
		vaccine.setText("All up to date");
		tr.setText("None needed currently");
		history.setText("No previous Ailments");
		allergies.setText("No current Allergies");
	}
	public void pickles() {
		vaccine.setText("Benadryl Application");
		tr.setText("None needed currently");
		history.setText("No previous Ailments");
		allergies.setText("Flea Allergy Dermatitis");
	}
	public void Oogway() {
		vaccine.setText("None Needed");
		tr.setText("None needed currently");
		history.setText("No previous Ailments");
		allergies.setText("No Current Allergies");
	}
	public void Snowbell() {
		vaccine.setText("All up to Date");
		tr.setText("None needed currently");
		history.setText("Severe Dairy Allergy");
		allergies.setText("No Current Allergies");
	}
	public void Oggy() {
		vaccine.setText("All up to Date");
		tr.setText("Pollen Allergy Treatemnt Needed");
		history.setText("No Previous Ailments");
		allergies.setText("Pollen Allergies");
	}
	public void Donatello() {
		vaccine.setText("None Needed");
		tr.setText("None needed currently");
		history.setText("Left Flipper Injured");
		allergies.setText("No Current Allergies");
	}
	
	public void createEvent(ActionEvent e){
		if ( c == 0) {
		event1.setText(eventName.getText());
		event1.setTextFill(Color.BLACK);
		
		time1.setText(timeField.getText());
		time1.setTextFill(Color.BLACK);

		date1.setText(dateField.getText());
		date1.setTextFill(Color.BLACK);}
		if (c == 1)
		{
			event2.setText(eventName.getText());
			event2.setTextFill(Color.BLACK);
			
			time2.setText(timeField.getText());
			time2.setTextFill(Color.BLACK);

			date2.setText(dateField.getText());
			date2.setTextFill(Color.BLACK);
		}
		c++;
	}
	
	private void switchScene(String fxmlFileName, ActionEvent event) throws IOException {
	    MenuItem menuItem = (MenuItem) event.getSource();
	    ContextMenu contextMenu = menuItem.getParentPopup();
	    Node ownerNode = contextMenu.getOwnerNode();
	    Stage currentStage = (Stage) ownerNode.getScene().getWindow();
	    root = FXMLLoader.load(getClass().getResource(fxmlFileName));
	    scene = new Scene(root);
	    currentStage.setScene(scene);
	    currentStage.show();
	}
	
	public void logOut(ActionEvent event) throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You are about to logout!");
		alert.setContentText("Are you sure you want to Logout?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			switchScene1(event);
		}
		
	}
	

	    public void switchToDonationManagement(ActionEvent event) throws IOException {
	        switchScene("DonationManagement.fxml", event);
	    }

	    public void switchToEventManagement(ActionEvent event) throws IOException {
	        switchScene("EventManagement.fxml", event);
	    }
	    public void switchToStaffScheduling(ActionEvent event) throws IOException {
	    	switchScene("StaffScheduling.fxml", event);
	    }
	    public void switchToFavourites(ActionEvent event) throws IOException {
	    	switchScene("Favourites.fxml", event);
	    }
	    public void switchToUserManagement(ActionEvent event) throws IOException {
	    	switchScene("UserManagement.fxml", event);
	    }
	    public void switchToHelp(ActionEvent event) throws IOException {
	    	switchScene("Help.fxml", event);
	    }
	    public void switchToSettings(ActionEvent event) throws IOException {
	    	switchScene("Settings.fxml", event);
	    }
	    public void switchToMedicalRecords(ActionEvent event) throws IOException {
	    	switchScene("MedicalRecords.fxml", event);
	    }
	    public void switchToRegistrationPage(ActionEvent event) throws IOException {
	    	switchScene("RegistrationPage.fxml", event);
	    }
	    public void switchToAdoptionFacilitation(ActionEvent event) throws IOException {
	    	switchScene("AdoptionFacilitation.fxml", event);
	    }
	    public void switchToAnimalInformationManagement(ActionEvent event) throws IOException {
	    	switchScene("AnimalInformationManagement.fxml", event);
	    }
	    public void switchToPetSphere(ActionEvent event) throws IOException {
	    	switchScene("Dashboard.fxml", event);
	    }
	    public void switchToHome(ActionEvent event) throws IOException {
	    	switchScene("HomePage.fxml", event);
	    }
	    
	    public void register(ActionEvent event) throws IOException{
	    	if(firstName.getText().equals("")||firstName.getText().equals("")||lastName.getText().equals("")||userName.getText().equals("")||password.getText().equals("")||confirmPassword.getText().equals(""))
	    	{
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setHeaderText("Please fill all fields");
	    		alert.showAndWait().get();
	    	}
	    	else
	    	{
	    		mail = email.getText();pass = password.getText();Uname = userName.getText(); cPass = confirmPassword.getText();
	    		if(!pass.equals(cPass)) {
    			Alert alert = new Alert(AlertType.ERROR);
 	    		alert.setHeaderText("Passwords Do not match. Please try again.");
 	    		alert.showAndWait().get();
 	    		return;
	    	}
	    	if(mail.substring(mail.indexOf('@')).equals("@ug.bilkent.edu.tr")) {
	    		Alert alert = new Alert(AlertType.ERROR);
 	    		alert.setHeaderText("You cannot add an admin email. Please try again.");
 	    		alert.showAndWait().get();
 	    		return;
	    	}
	    		
	    	users.add(Uname);
	    	emails.add(mail);
	    	
	    	Boolean dupUser = false;
	    	Boolean dupMail = false;
	    	Boolean dupPass = false;
	    	
	    	for(int i = 0; i<users.size()-1;i++) {
	    		for(int j = i+1;j<users.size();j++) {
	    			if(users.get(i).equals(users.get(j))) {
	    				dupUser = true;
	    				System.out.println(users.get(i)+" " + users.get(j));
	    				Alert alert = new Alert(AlertType.ERROR);
	     	    		alert.setHeaderText("This username already exists. Please try again.");
	     	    		alert.showAndWait().get();
	     	    		users.remove(Uname);
	     	    		emails.remove(mail);
	     	    		return;
	    			}
	    			
	    		}
	    		}
	    	for(int i = 0; i<emails.size()-1;i++) {
	    		for(int j = i+1;j<emails.size();j++) {
	    			if(emails.get(i).equals(emails.get(j))) {
	    				dupUser = true;
	    				Alert alert = new Alert(AlertType.ERROR);
	     	    		alert.setHeaderText("This email already exists. Login to continue.");
	     	    		alert.showAndWait().get();
	     	    		emails.remove(mail);
	     	    		users.remove(Uname);
	     	    		return;
	    			}
	    		}
	    		}
	    	users.remove(users.size()-1);
	    	emails.remove(emails.size()-1);
	    	
	    	if (!dupUser&&!dupMail) {
	    		String content = Uname + " " + mail + " " + pass;
		    	makeFileUser(content);
		    	
	    	root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();}
	        
	        }
	    	
	    }
	    private final  String FILE_NAME = "Data.txt";

	    public void makeFileUser(String content) {
		        try (FileWriter fileWriter = new FileWriter(FILE_NAME, true)) {
		            fileWriter.write(content + System.lineSeparator());
		            System.out.println("Content added to '" + FILE_NAME + "' successfully!");
		        } catch (IOException e) {
		            System.err.println("Error writing to file: " + e.getMessage());
		        }
	    	
	    }
	    private void modifyFile( int lineIndex, int wordIndex, String newWord) {
	        try {
	            // Convert the file name to a Path
	            Path filePath = Paths.get(FILE_NAME);

	            // Read all lines from the file
	            List<String> lines = Files.readAllLines(filePath);

	            // Modify the desired line
	            if (lineIndex >= 1 && lineIndex <= lines.size()) {
	                lines.set(lineIndex - 1, changeWord(lines.get(lineIndex - 1), newWord, wordIndex));
	            } else {
	                // Handle invalid line index (you may throw an exception or log a message)
	                System.out.println("Invalid line index: " + lineIndex);
	            }

	            // Update the content of the file
	            Files.write(filePath, lines);
	        } catch (IOException e) {
	            // Handle the exception here or log the error
	            e.printStackTrace();
	        }
	    }

	    private String changeWord(String sentence, String newWord, int wordIndex) {
	        // Split the sentence into words
	        String[] words = sentence.split("\\s");

	        // Check if the word index is valid
	        if (wordIndex >= 1 && wordIndex <= words.length) {
	            // Replace the word at the specified index
	            words[wordIndex - 1] = newWord;
	        } else {
	            // Handle invalid word index (you may throw an exception or log a message)
	            System.out.println("Invalid word index: " + wordIndex);
	        }

	        // Join the words back into a sentence
	        return String.join(" ", words);
	    }	   
	    public void addUserToLists() throws IOException {
	    	BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
	    	 String line;

	            while ((line = reader.readLine()) != null) {
	                String[] sentences = line.split("[!?]"); // Split the line into sentences

	                for (String sentence : sentences) {
	                    String trimmedSentence = sentence.trim();
	                    if (!trimmedSentence.isEmpty()) {
	                        String[] words = trimmedSentence.split("\\s+"); // Split the sentence into words
	                        if (words.length > 0) {
	                            users.add(words[0]); // Add the first word to the ArrayList
	                        }
	                    }
	                }
	            }

	        }
	    
		public void addEmailToLists() throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			String line;

            while ((line = reader.readLine()) != null) {
                String[] sentences = line.split("[!?]"); 

                for (String sentence : sentences) {
                    String trimmedSentence = sentence.trim();
                    if (!trimmedSentence.isEmpty()) {
                        String[] words = trimmedSentence.split("\\s+"); 
                        if (words.length > 1) {
                            emails.add(words[1]); 
                        }
                    }
                }
            }

        }
		public void addPasswordsToLists() {try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] sentences = line.split("[!?]"); 

                for (String sentence : sentences) {
                    String trimmedSentence = sentence.trim();
                    if (!trimmedSentence.isEmpty()) {
                        String[] sentenceWords = trimmedSentence.split("\\s+"); 
                        if (sentenceWords.length > 2) {
                            passwords.add(sentenceWords[2]);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }}
	    public void login(ActionEvent event) throws IOException {
	    	if(userEmail.getText().equals("")||userPassword.getText().equals("")) {
	    		Alert alert = new Alert(AlertType.ERROR);
 	    		alert.setHeaderText("Fields are empty. Please enter a valid email");
 	    		alert.showAndWait().get();
 	    		return;
	    	}
	    	String naam = "";
	    	Boolean emailVar = false;
	    	Boolean passwordVar = false;
	    	String offmail = "";
	    	int count = 0;
	    	for(int i = 0; i<emails.size();i++) {
	    		if(emails.get(i).equals(userEmail.getText())) {
	    			emailVar = true;
	    			count = i;
	    			offmail = emails.get(i);
	    			break;
	    		}
	    	}
	    	if(!emailVar) {
	    		Alert alert = new Alert(AlertType.ERROR);
 	    		alert.setHeaderText("Please enter a valid email");
 	    		alert.showAndWait().get();
 	    		return;
	    	}
	    	for(int i = 0; i<passwords.size();i++) {
	    		if(count == i&&passwords.get(i).equals(userPassword.getText())) {
	    			passwordVar = true;
	    			break;
	    		}
	    	}
	    	if(!passwordVar) {
	    		Alert alert = new Alert(AlertType.ERROR);
 	    		alert.setHeaderText("Please enter a valid password");
 	    		alert.showAndWait().get();
 	    		return;
	    	}
	    	for(int i = 0; i<users.size();i++) {
	    		if(count == i) {
	    			naam = users.get(i);
	    			break;
	    		}
	    	}
	    	//System.out.println(userSetName.getText());
	    	//userSetName.setText("Isa");

	    	if(offmail.substring(offmail.indexOf('@')).equals("@ug.bilkent.edu.tr")) {
	    		root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
				stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		        scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		        
	    	}
	    	
	    	else{root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();}
	    }
	}

	
