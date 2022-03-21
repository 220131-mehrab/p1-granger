package com.revature.fruitreview;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class App {
    public static void main(String[] args) throws SQLException {
        //Connecting to H2 database
        String url = "jdbc:h2:mem:fruit";
        String username = "sa";
        String password = "";
        Connection connection = DriverManager.getConnection(url + ";INIT=runscript from 'classpath:schema.sql'", username, password);
        ResultSet results = connection.prepareStatement("SELECT * FROM fruitreviews").executeQuery();

        ArrayList<FruitRating> fruitList = new ArrayList<>();
        while(results.next()) {
            FruitRating review = new FruitRating();
            review.setId(results.getInt("ReviewId"));
            review.setFruitName(results.getString("FruitName"));
            review.setRating(results.getInt("Rating"));
            review.setReview(results.getString("Review"));
            fruitList.add(review);
        }

        ObjectMapper mapper = new ObjectMapper();

        //Starting Tomcat Server
        FruitServer fruitServer = new FruitServer();
        Tomcat server = fruitServer.getTomcat();
        try {
            server.start();
        } catch (LifecycleException eLife) {
            System.err.println("Server did not start\n"+eLife.getMessage());
        }
        server.addServlet("", "fruitServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                resp.getWriter().println(mapper.writeValueAsString(fruitList));
            }
            
        }).addMapping("/fruit");
    }
}
