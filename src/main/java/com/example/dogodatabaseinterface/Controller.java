package com.example.dogodatabaseinterface;

import javafx.fxml.FXML;

public class Controller {
    @FXML
User user=new User (  );

    @FXML
    protected  void usernameClick(){
        user.usernameProperty.addListener ( v->{
            user.setUsernameProperty ( String.valueOf ( v ) );


        } );}
        @FXML
        protected  void passwordClick(){
            user.usernameProperty.addListener ( v->{
                user.setPasswordProperty ( String.valueOf ( v ) );


            } );


            }
}