package com.example.dogodatabaseinterface;

public class Database {

    String jdbcUrl = "jdbc:mysql://localhost:3306/Userdb";
    String username = "root";
    String password = "";

    public Database ( ) {
    }

    public String getJdbcUrl ( ) {
        return jdbcUrl;
    }

    public void setJdbcUrl ( String jdbcUrl ) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername ( ) {
        return username;
    }

    public void setUsername ( String username ) {
        this.username = username;
    }

    public String getPassword ( ) {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }
}
