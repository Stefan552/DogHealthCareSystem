package com.example.dogodatabaseinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DogHealthApplication extends Application {



    @Override
    public void start ( Stage stage ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader ( DogHealthApplication.class.getResource ( "loginInterface.fxml" ) );
        Scene scene = new Scene ( fxmlLoader.load ()  );
        stage.setTitle ( "DogDatabaseLoginPage" );
        stage.setResizable ( false );
        stage.centerOnScreen ();
        stage.setScene ( scene );
        stage.show ();
    }


    public static void main ( String[] args ) {
        launch (  );


    }
}