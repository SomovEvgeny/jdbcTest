package com.company;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

public class Main {
    private static String url;
    private static String user;
    private static String password;

    public static void main(String[] args) {
	// write your code here
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        try{
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream("database.properties.txt");
            prop.load(in);
            url = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.user");
            password = prop.getProperty("jdbc.password");

        }
        catch (IOException e){
            e.printStackTrace();
        }

        Connection connection = null;


        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement state = connection.createStatement();
            state.executeUpdate("insert into goods values (2,14)");
            ResultSet result = state.executeQuery("select * from goods");
            while(result.next()){
                System.out.println(result.getString(1)+" | "+ result.getString(2));
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
