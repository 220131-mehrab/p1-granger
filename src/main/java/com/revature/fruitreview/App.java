package com.revature.fruitreview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) {
        //Connecting to H2 database
        String url = "jdbc:h2:mem:test";
        String username = "sa";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        } catch (SQLException eSQL) {
            System.err.println("Could not find SQL database\n"+eSQL.getMessage());
        }

        //Starting Tomcat Server
        FruitServer fruitServer = new FruitServer();
        Tomcat server = fruitServer.getTomcat();
        try {
            server.start();
        } catch (LifecycleException eLife) {
            System.err.println("Server did not start\n"+eLife.getMessage());
        }
    }
}
