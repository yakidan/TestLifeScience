package com.lifescience.database;


import com.lifescience.business.Book;

import java.sql.*;
import java.util.HashMap;

public class JDBCPostgreSQL {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "qwerty1234";

    Connection connection = null;
    Statement statement = null;

    public boolean connect() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return false;
        }
        connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return true;
    }


    public ResultSet query(String sql) {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) { }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }
    public void execute(String sql) {
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    connection.close();
            } catch (SQLException se) { }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}