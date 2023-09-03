package com.example.dogodatabaseinterface;

import javafx.beans.property.StringProperty;

public class User {

    StringProperty usernameProperty;
    StringProperty passwordProperty;

    public User ( ) {
    }

    public String getUsernameProperty ( ) {
        return usernameProperty.get ();
    }

    public StringProperty usernamePropertyProperty ( ) {
        return usernameProperty;
    }

    public void setUsernameProperty ( String usernameProperty ) {
        this.usernameProperty.set ( usernameProperty );
    }

    public String getPasswordProperty ( ) {
        return passwordProperty.get ();
    }

    public StringProperty passwordPropertyProperty ( ) {
        return passwordProperty;
    }

    public void setPasswordProperty ( String passwordProperty ) {
        this.passwordProperty.set ( passwordProperty );
    }

    public User ( StringProperty usernameProperty , StringProperty passwordProperty ) {
        this.usernameProperty = usernameProperty;
        this.passwordProperty = passwordProperty;
    }
}
