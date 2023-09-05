package com.example.dogodatabaseinterface;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
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

public class DatabaseController {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

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
    private TextArea resultArea;
    @FXML
    private Text text;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void searchClients() {
        String jdbcUrl =  "jdbc:mysql://localhost:3306/Userdb";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection error: " + e.getMessage());
        }

        String firstName1 = firstName.getText();
        String lastName1 = lastName.getText();



        try {
            String query = "SELECT * FROM Clients WHERE firstname = ? AND lastname = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,   firstName1 );
            preparedStatement.setString(2,   lastName1 );



            resultSet = preparedStatement.executeQuery();

            StringBuilder resultBuilder = new StringBuilder();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String clientFirstName = resultSet.getString("firstname");
                String clientLastName = resultSet.getString("lastname");
                String phoneNumber = resultSet.getString("phoneNumber");
                String city = resultSet.getString("city");
                String petName = resultSet.getString("petName");
                String breed = resultSet.getString("breed");
                String vaccinated=resultSet.getString ( "vaccinated" );
                String age=resultSet.getString ( "age");
                String vetPoint=resultSet.getString ( "veterinary_healthpoint" );


                resultBuilder.append("Client ").append("\n");
                resultBuilder.append("Database Id: ").append(id).append("\n");
                resultBuilder.append("First Name: ").append(clientFirstName).append("\n");
                resultBuilder.append("Last Name: ").append(clientLastName).append("\n");
                resultBuilder.append("Phone Number: ").append(phoneNumber).append("\n");
                resultBuilder.append("City: ").append(city).append("\n");
                resultBuilder.append("Pet Name: ").append(petName).append("\n");
                resultBuilder.append("Breed: ").append(breed).append("\n");
                resultBuilder.append ( "Vaccinated with VCAN1015: " ).append ( vaccinated ).append("\n");
                resultBuilder.append ( "Age: " ).append ( age ).append("\n");
                resultBuilder.append ( "Treated at: " ).append ( vetPoint ).append("\n");

            }

           if ( resultBuilder.isEmpty () == true ){
               resultArea.setText ( "Client not found in the database." );
               text.setText ( "Error 404 " );
            PauseTransition pausee = new PauseTransition( Duration.seconds (5));
            pausee.setOnFinished(e -> {
                resultArea.setText ( "" );
                firstName.setText ( "" );
                lastName.setText ( "");
                text.setText ( "" );
           });pausee.play ();}else{ resultArea.setText(resultBuilder.toString());
            text.setText("Got It!");

            PauseTransition pause = new PauseTransition( Duration.seconds (5));
            pause.setOnFinished(e -> {
             text.setText ( "" );
             firstName.setText ( "" );
             lastName.setText ( "");

            });
            pause.play ();}
        } catch (SQLException e) {
            e.printStackTrace();
            text.setText("Database query error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        switchToLoginInterface ( event );
    }

    private void switchToLoginInterface( ActionEvent event) throws IOException {
        root = FXMLLoader.load ( getClass ().getResource ( "loginInterface.fxml" ) );
        stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        stage.setResizable ( false );
        scene = new Scene ( root );
        stage.setScene ( scene );
        stage.centerOnScreen ();
        stage.show ();

    }
}
