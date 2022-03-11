package com.revature.fruitreview;

import java.io.IOException;
import java.io.InputStream;

import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FruitServer {
    Tomcat tomcat;

    public FruitServer() {
        tomcat = new Tomcat();
        tomcat.getConnector();
        tomcat.addContext("",null);
        tomcat.addServlet("", "defaultServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String filename = req.getPathInfo();
                String resourceDir = "static";
                InputStream file = getClass().getClassLoader().getResourceAsStream(resourceDir+filename);
                IOUtils.copy(file, resp.getOutputStream());
            }
        }).addMapping("/*");
    }

    public Tomcat getTomcat() {
        return tomcat;
    }
}
