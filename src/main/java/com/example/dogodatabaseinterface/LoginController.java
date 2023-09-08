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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Stage stage2;
    private Scene scene2;
    private Parent root2;
    private Stage stage3;
    private Scene scene3;
    private Parent root3;
    @FXML
    private Label resultText;
    @FXML
    private TextArea usernameTextArea;

    @FXML
    private TextArea passwordTextArea;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel1;


    @FXML
    private void loginButtonClicked ( ActionEvent event ) {
        String username = usernameField.getText ();
        String password = passwordField.getText ();

        String jdbcURL = "jdbc:mysql://localhost:3306/Userdb";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            Connection connection = DriverManager.getConnection ( jdbcURL , dbUsername , dbPassword );

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement ( sql );
            statement.setString ( 1 , username );
            statement.setString ( 2 , password );

            ResultSet resultSet = statement.executeQuery ();

            if ( resultSet.next () && !username.isBlank () && !password.isBlank () ) {

                loginLabel1.setText ( "Login successful!" );
                PauseTransition pause = new PauseTransition ( Duration.seconds ( 3 ) );
                pause.setOnFinished ( e -> {
                    loginLabel1.setText ( "" );
                    usernameField.setText ( "" );
                    passwordField.setText ( "" );
                } );
                pause.play ();
                switchToDtabaseInterface ( event );


            } else {
                loginLabel1.setText ( "Please try again!" );
                loginButton.setText ( "Access Denied" );
                PauseTransition pause = new PauseTransition ( Duration.seconds ( 3 ) );
                pause.setOnFinished ( e -> {
                    usernameField.setText ( "" );
                    passwordField.setText ( "" );
                    loginButton.setText ( "Log In" );
                    loginLabel1.setText ( "" );
                } );
                pause.play ();
            }

            resultSet.close ();
            statement.close ();
            connection.close ();

        } catch (SQLException e) {
            e.printStackTrace ();
        } catch (Exception e) {
            throw new RuntimeException ( e );
        }
    }

    private void switchToDtabaseInterface ( ActionEvent event ) throws IOException {
        root = FXMLLoader.load ( getClass ().getResource ( "databaseInterface.fxml" ) );
        stage = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
        stage.setResizable ( false );
        scene = new Scene ( root );
        stage.setScene ( scene );
        stage.centerOnScreen ();
        stage.show ();
    }

    public void signUpMethod(ActionEvent event) throws IOException {
        signupButtonClicked (  event);
}
    @FXML
    private void signupButtonClicked ( ActionEvent event ) throws IOException {


            root2 = FXMLLoader.load ( getClass ().getResource ( "addUserInterface.fxml" ) );
            stage2 = (Stage) ((Node) event.getSource ()).getScene ().getWindow ();
            stage2.setResizable ( false );
            scene2 = new Scene ( root2 );
            stage2.setScene ( scene2 );
            stage2.centerOnScreen ();
            stage2.show ();
        }

    @FXML
    public void addUser(ActionEvent event) throws IOException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Userdb";
        String dbUsername = "root";
        String dbPassword = "";

        String usernameInput = usernameTextArea.getText();
        String passwordInput = passwordTextArea.getText();

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usernameInput);
            preparedStatement.setString(2, passwordInput);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0 && !usernameInput.isBlank ()&& !passwordInput.isBlank ()) {
                resultText.setText ( "User added to the Database!" );
                PauseTransition pause = new PauseTransition( Duration.seconds (2));
                pause.setOnFinished(e -> {
                    usernameTextArea.setText ( "" );
                    passwordTextArea.setText ( "");
                    try {
                        switchbackInterface ( event );
                    } catch (IOException ex) {
                        throw new RuntimeException ( ex );
                    }
                });pause.play ();

            } else { resultText.setText("Failed to add user.");
                PauseTransition pause1 = new PauseTransition( Duration.seconds (2));
                pause1.setOnFinished(e -> {
                    usernameTextArea.setText ( "" );
                    passwordTextArea.setText ( "");
                    resultText.setText ( "" );

                });pause1.play ();


            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            resultText.setText("Database connection error: " + e.getMessage());
        }
    }

    @FXML
    public void cancelButtonClicked(ActionEvent event) throws IOException {
        switchbackInterface ( event );
    }

    private void switchbackInterface(ActionEvent event) throws IOException {
        root3 = FXMLLoader.load(getClass().getResource("loginInterface.fxml"));
        stage3 = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage3.setResizable ( false );
        scene3 = new Scene(root3);
        stage3.setScene(scene3);
        stage3.centerOnScreen ();
        stage3.show();
    }
}
