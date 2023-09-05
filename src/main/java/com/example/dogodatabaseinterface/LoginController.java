package com.example.dogodatabaseinterface;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class LoginController  {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel1;



    @FXML
    private void loginButtonClicked(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        String jdbcURL = "jdbc:mysql://localhost:3306/Userdb";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL,dbUsername,dbPassword);

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                loginLabel1.setText("Login successful!");
                PauseTransition pause = new PauseTransition( Duration.seconds(3));
                pause.setOnFinished(e -> {
                    loginLabel1.setText("");
                    usernameField.setText ( "" );
                    passwordField.setText ( "" );
                });
                pause.play ();
              switchToDtabaseInterface ( event );




            } else {
                loginLabel1.setText ( "Please try again!" );
                loginButton.setText("Access Denied");
                PauseTransition pause = new PauseTransition( Duration.seconds(3));
                pause.setOnFinished(e -> {
                    usernameField.setText ( "" );
                    passwordField.setText ( "" );
                        loginButton.setText("Log In");
                        loginLabel1.setText ( "" );
                });
                pause.play ();
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException ( e );
        }
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

    // TODO: 04.09.2023  
    @FXML
    private void signupButtonClicked(ActionEvent event) {
        // Implement your signup logic here
    }
}
