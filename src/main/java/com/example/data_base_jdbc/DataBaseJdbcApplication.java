package com.example.data_base_jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.sql.*;

@SpringBootApplication
public class DataBaseJdbcApplication {

    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String SCEMA_NAME = "msg_java_training_schema";
    static String DB_URL = "jdbc:mysql://localhost:3306/" + SCEMA_NAME;
    static String USER = "root";
    static String PASSWORD = "password";
    private static final String UPDATE_SQL = "UPDATE Author Set lastname = ? WHERE lastname = ?";
private  static final String INSERT_SQL = "INSERT INTO Document values (?,?,?)";

    private static InputStream readFile(String path) throws FileNotFoundException{
        File f = new File(path);
        return new FileInputStream(f);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(DataBaseJdbcApplication.class, args);
       Tasks tasks = new Tasks();

       try {
           tasks.Task1();
           tasks.Task2();
         //  tasks.Task3(); //if not working increment second setInt second parameter
         //  tasks.Task4(); //if not working increment second setInt second parameter
         //  tasks.Task5(); //if not working increment second setInt second parameter
       }
       catch (SQLException error){
           System.err.println("SQL Query error somewhere");
           error.printStackTrace();
       }

    }

}