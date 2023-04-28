package org.joaocastillo.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection connection;

    // Constructor
    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentavehiculos?useSSL=false", "root", "root");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Singleton
    public static Conexion getInstance() {
        if (instance == null) instance = new Conexion();
        return instance;
    }

    // Getters
    public Connection getConnection() {
        return connection;
    }

    // Setters
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
