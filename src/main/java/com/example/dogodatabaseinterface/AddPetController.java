package com.example.dogodatabaseinterface;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddPetController {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextArea firstName;
    @FXML
    private TextArea lastName;
    @FXML
    private TextArea phoneNumber;
    @FXML
    private TextArea city;
    @FXML
    private TextArea petName;
    @FXML
    private TextArea breed;
    @FXML
    private TextArea vaccinated;
    @FXML
    private TextArea age;
    @FXML
    private TextArea vetPoint;
    @FXML
    private Text resultArea;


    @FXML
    public void addClient() {

        Database database=new Database ();
        try {
            connection = DriverManager.getConnection( database.getJdbcUrl (), database.getUsername (), database.getPassword ());
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText ("Database connection error: " + e.getMessage());
        }

        String firstName1 = firstName.getText();
        String lastName1 = lastName.getText();
        String phoneNumber1 = phoneNumber.getText();
        String city1 = city.getText();
        String petName1 = petName.getText();
        String breed1 = breed.getText();
        String vaccinated1 = vaccinated.getText();
        String age1 = age.getText();
        String vetPoint1 = vetPoint.getText();

        try {
            String query = "INSERT INTO Clients (firstname, lastname, phoneNumber, city, petName, breed, vaccinated, age, veterinary_healthpoint) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName1);
            preparedStatement.setString(2, lastName1);
            preparedStatement.setString(3, phoneNumber1);
            preparedStatement.setString(4, city1);
            preparedStatement.setString(5, petName1);
            preparedStatement.setString(6, breed1);
            preparedStatement.setString(7, vaccinated1);
            preparedStatement.setString(8, age1);
            preparedStatement.setString(9, vetPoint1);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                resultArea.setText("Client added successfully!");
                PauseTransition pause = new PauseTransition(Duration.seconds(10));
                pause.setOnFinished(e -> {
                    resultArea.setText("");
                    firstName.setText("");
                    lastName.setText("");
                    phoneNumber.setText("");
                    city.setText("");
                    petName.setText("");
                    breed.setText("");
                    vaccinated.setText("");
                    age.setText("");
                    vetPoint.setText("");
                });
                pause.play();
            } else {
                resultArea.setText("Failed to add client.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Please fill up the fields with data!");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void refreshButtonClick(){

        resultArea.setText("");
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        city.setText("");
        petName.setText("");
        breed.setText("");
        vaccinated.setText("");
        age.setText("");
        vetPoint.setText("");
}

    @FXML
    private void cancelClick( Event event ) throws IOException {
        switchToDtabaseInterface ( (ActionEvent) event );
}

    private void switchToDtabaseInterface(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("databaseInterface.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable ( false );
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen ();
        stage.show();
    }

}
