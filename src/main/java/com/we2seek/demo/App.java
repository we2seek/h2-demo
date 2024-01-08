package com.we2seek.demo;

import java.sql.*;

/**
 * Hello world!
 */
@SuppressWarnings("SqlNoDataSourceInspection")
public class App {

    private static final String TABLE_NAME = "employee";

    public static void main(String[] args) {

        Connection connection = null;

        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:/tmp/test-database", "sa", "");

            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE " + TABLE_NAME + " IF EXISTS");
            statement.execute("CREATE TABLE " + TABLE_NAME + " (id INTEGER NOT NULL, name VARCHAR(32) NOT NULL, PRIMARY KEY (id))");
            statement.execute("INSERT INTO " + TABLE_NAME + " (id, name) VALUES (100, 'First User'), (102, 'Third User'), (101, 'Second User')");

            ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY id");
            while (rs.next()) {
                System.out.printf("%d: %s%n", rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
