package com.example.demo.ivanovds;

import java.io.IOException;
import java.sql.*;
import java.io.*;

public class DB
{
    public static void main(String[] args){
    }

    private  String URL;// = "jdbc:postgresql://localhost:5432/db";
    private  String USERNAME ;//= "postgres";
    private  String PASSWORD; //= "123";


    private static Connection connection;

    public static void setConnection() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите URL базы данных: ");
        String URL = reader.readLine();
        System.out.print("\nВведите логин (Для БД): ");
        String USERNAME = reader.readLine();
        System.out.print("\nВведите пароль (Для БД): ");
        String PASSWORD = reader.readLine();

    try{
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Есть подключение к БД.");
    }
    catch (SQLException throwables)
    {
        throwables.printStackTrace();
        System.out.println("Error DB");
    }

    }
    public static void sql (String pathToCsv , String pathWhenCSV) throws SQLException {
        Statement statement = connection.createStatement();

        try {statement.executeUpdate("CREATE TABLE FILES (mark varchar(25) not null, sum int not null )");
             statement.executeUpdate("COPY files FROM '"+pathToCsv+"' DELIMITER ','");
           // PreparedStatement pstmt = connection.prepareStatement("COPY files FROM ? DELIMITER ',' ");
          //  pstmt.setString(0, pathToCsv);
            statement.executeUpdate("CREATE TABLE provercka (mark varchar(25) not null, sum int not null )");
            statement.executeUpdate("INSERT INTO provercka SELECT mark,sum(sum) from files group by mark");
             statement.executeUpdate("COPY provercka TO '"+pathWhenCSV+"' DELIMITER ',' ");
           // PreparedStatement preparedStatement = connection.prepareStatement("COPY provercka TO ? DELIMITER ',' ");
          //  preparedStatement.setString(1,pathWhenCSV);
            statement.executeUpdate("drop table files");
            statement.executeUpdate("drop table provercka");

        }
        catch (SQLException throwables) {
            System.out.println("Error!SQL");
            statement.close();
        }


}
}
/*statement.executeUpdate("CREATE TABLE FILES (mark varchar(25) not null, sum int not null )");
        // statement.executeUpdate("COPY files FROM 'D:\\dekstop\\Programming\\source01.csv' DELIMITER ','");
        PreparedStatement pstmt = connection.prepareStatement("COPY files FROM ? DELIMITER ',' ");
        pstmt.setString(0, pathToCsv);
        statement.executeUpdate("CREATE TABLE provercka (mark varchar(25) not null, sum int not null )");
        statement.executeUpdate("INSERT INTO provercka\n" +
        "SELECT mark,sum(sum) from files\n" +
        "group by mark");
        // statement.executeUpdate("COPY provercka TO 'C:\\DB\\check02.csv' DELIMITER ',' ");
        PreparedStatement preparedStatement = connection.prepareStatement("COPY provercka TO ? DELIMITER ',' ");
        preparedStatement.setString(1,pathWhenCSV);
        statement.executeUpdate("drop table files");
        statement.executeUpdate("drop table provercka");

 */