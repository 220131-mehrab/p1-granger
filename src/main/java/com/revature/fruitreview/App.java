package com.revature.fruitreview;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) {
        FruitServer fruitServer = new FruitServer();
        Tomcat server = fruitServer.getTomcat();
        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("Server did not start");
        }
    }
}
