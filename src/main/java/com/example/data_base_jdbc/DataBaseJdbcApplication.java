package com.example.data_base_jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class DataBaseJdbcApplication {

    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String SCEMA_NAME = "msg_java_training_schema";
    static String DB_URL = "jdbc:mysql://localhost:3306/" + SCEMA_NAME;
    static String USER = "root";
    static String PASSWORD = "password";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(DataBaseJdbcApplication.class, args);
        Class.forName(JDBC_DRIVER);
//Connecting to DB Server
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        System.out.println("Connected successfully");

//Creating and executing a statement
        String sql = "SELECT id, firstname, lastname FROM Author";
        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(sql);

//Processing the information
        System.out.println("Current status of DB");

        System.out.println("ID\tFirstname\tlastname");
        while (rs.next()) {
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            int id = rs.getInt("id");
            System.out.println(id + "\t" + firstname + "\t" + lastname);
        }





//We have to close explicitly the connection
        connection.close();
    }

}