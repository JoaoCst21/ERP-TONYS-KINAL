package org.joaocastillo.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection connection;

    private boolean isProduction = false;
    private String URL_HOST = isProduction ? "jdbc:mysql://containers-us-west-163.railway.app:7417" : "jdbc:mysql://localhost:3306";
    private String DATABASE = "DBTonysKinal2023";
    private String USER = "root";
    private String PASSWORD = isProduction ? "EAFkW5NszMgSxhMQ18xU" : "admin"; // "K$oport3Lab" : "root"; - "admin" : "root";

    // Constructor
    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println(URL_HOST + "/" + DATABASE + "?enabledTLSProtocols=TLSv1.2");
            System.out.println("jdbc:mysql://localhost:3306/rentavehiculos?useSSL=false");
            connection = DriverManager.getConnection(URL_HOST + "/" + DATABASE + "?useSSL=false", USER, PASSWORD);
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
