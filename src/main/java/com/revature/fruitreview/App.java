package com.revature.fruitreview;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Tomcat server = new Tomcat();
        server.getConnector();
        server.addContext("",null);
        server.addServlet("", "defaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getWriter().println("Hello");
            }
        }).addMapping("/*");
        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("Server did not start");
        }
    }
}
