package com.example.data_base_jdbc;

import java.io.*;
import java.sql.*;

import static com.example.data_base_jdbc.DataBaseJdbcApplication.JDBC_DRIVER;


public class Tasks {

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
      public void Task1() throws ClassNotFoundException, SQLException {
          Class.forName(JDBC_DRIVER);
          //Connecting to DB Server
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
          System.out.println("Connected successfully");
          //Creating and executing a statement
          String sql = "SELECT id, firstname, lastname FROM Author";
          Statement statement = connection.createStatement();


          ResultSet rs = statement.executeQuery(sql);

          //Processing the information
          System.out.println("\nCurrent status of DB:\n");

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

      public void Task2() throws ClassNotFoundException, SQLException {
          Class.forName(JDBC_DRIVER);
          //Connecting to DB Server
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
          System.out.println("Connected successfully");
          PreparedStatement statement1 = connection.prepareStatement(UPDATE_SQL);
          statement1.setString(2, "Billa");
          statement1.setString(1, "Lidl");

          statement1.executeUpdate();
          //We have to close explicitly the connection
          connection.close();
      }

      public void Task3() throws ClassNotFoundException, SQLException {
          Class.forName(JDBC_DRIVER);
          //Connecting to DB Server
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
          System.out.println("Connected successfully");
          String sqlSelect = "SELECT * FROM Document";

          PreparedStatement statement2 = connection.prepareStatement(INSERT_SQL);
          statement2.setInt(1, 9);// if not working increment second parameter
          statement2.setString(2,"Test");
          try {
              InputStream stream = readFile("message.txt");
              statement2.setBlob(3,stream);
          } catch (FileNotFoundException e) {
              System.out.println("Cannot read fro file");
          }
          statement2.executeUpdate();

          File newFile = new File("messagenew.txt");
          try {
              OutputStream outputStream = new FileOutputStream(newFile);
              Statement statement3 = connection.createStatement();
              ResultSet rs2 =  statement3.executeQuery(sqlSelect);
              while (rs2.next()){
                  InputStream inputStream = rs2.getBinaryStream("mydata");
                  outputStream.write(inputStream.readAllBytes());

              }
              outputStream.flush();
              outputStream.close();

          } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          //We have to close explicitly the connection
          connection.close();
      }

      public void Task4() throws ClassNotFoundException, SQLException {
          Class.forName(JDBC_DRIVER);
          //Connecting to DB Server
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
          System.out.println("Connected successfully");
          String sql2 = "INSERT INTO Author VALUES (?,?,?)";
          try  {
              PreparedStatement statement4 = connection.prepareStatement(sql2);
              connection.setAutoCommit(false);
              statement4.setInt(1,14);// if not working increment second parameter
              statement4.setString(2,"Vasile");
              statement4.setString(3,"Gheorghe");
              statement4.addBatch();
              statement4.setInt(1,15);// if not working increment second parameter
              statement4.setString(2,"Vasile2");
              statement4.setString(3,"Gheorghe3");
              statement4.addBatch();
              statement4.executeBatch();
              connection.commit();
          } catch (SQLException e){
              System.out.println("Smth wrong with execution");
              try {
                  connection.rollback();
              }
              catch (SQLException e1){
                  System.out.println("Cannot Rollback");
              }
              throw new RuntimeException(e);
          }
          //We have to close explicitly the connection
          connection.close();
      }

      public void Task5() throws ClassNotFoundException, SQLException {
          Class.forName(JDBC_DRIVER);
          //Connecting to DB Server
          Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
          System.out.println("Connected successfully");
          String sql3 = "INSERT INTO Author VALUES (?,?,?)";
          try  {
              PreparedStatement statement5 = connection.prepareStatement(sql3);
              connection.setAutoCommit(false);
              for(int i= 16; i<102;i++)// if not working increment i
                  statement5.setInt(1,i);
              statement5.setString(2,"Vasile");
              statement5.setString(3,"Gheorghe");
              statement5.addBatch();
              statement5.executeBatch();
              connection.commit();
          } catch (SQLException e){
              System.out.println("Smth wrong with execution");
              try {
                  connection.rollback();
              }
              catch (SQLException e1){
                  System.out.println("Cannot Rollback");
              }
              throw new RuntimeException(e);
          }
          //We have to close explicitly the connection
          connection.close();
      }

}
