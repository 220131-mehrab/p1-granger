package com.revature.fruitreview;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                ResultSet results;
                try {
                    results = connection.prepareStatement("SELECT * FROM fruitreviews").executeQuery();
                    ArrayList<FruitRating> fruitList = new ArrayList<>();
                    while(results.next()) {
                        FruitRating review = new FruitRating();
                        review.setReviewId(results.getInt("ReviewId"));
                        review.setFruitName(results.getString("FruitName"));
                        review.setRating(results.getInt("Rating"));
                        review.setReview(results.getString("Review"));
                        fruitList.add(review);
                    }
                    resp.getWriter().println(mapper.writeValueAsString(fruitList));
                } catch (SQLException e) {
                    System.err.println("Failed to get from DB"+e.getMessage());
                }
            }

            @Override
            protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                FruitRating fruitRating = mapper.readValue(req.getInputStream(), FruitRating.class);
                try {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO fruitreviews VALUES (?, ?, ?, ?);");
                    statement.setInt(1, fruitRating.getReviewId());
                    statement.setString(2, fruitRating.getFruitName());
                    statement.setInt(3, fruitRating.getRating());
                    statement.setString(4, fruitRating.getReview());
                    statement.executeUpdate();
                    System.out.println("Inserted to DB"+fruitRating);
                } catch (SQLException e) {
                    System.err.println("Failed to insert" + e.getMessage());
                }
            }
            
        }).addMapping("/fruit");
    }
}
