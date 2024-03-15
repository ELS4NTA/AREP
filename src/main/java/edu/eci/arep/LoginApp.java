package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.eci.arep.service.SecureURLReader;
import edu.eci.arep.service.UserServiceMap;

/**
 * The LoginApp class represents a web application that provides a service to
 * authenticate and authorize users, and to access a secure external URL.
 * 
 * @author Daniel Santanilla
 */
public class LoginApp {

    /**
     * The main method of the web application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserServiceMap userService = UserServiceMap.getInstance();
        // API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure("certificates/ecikeystore.p12", "123456", null, null);
        port(getPort());

        staticFiles.location("/public");

        post("/login", (req, res) -> {
            String body = req.body();
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();
            if (userService.validateUser(json.get("username").getAsString(), json.get("password").getAsString())) {
                userService.addToWhitelist(json.get("username").getAsString());
                return "candies.html";
            } else {
                return "Invalid user or password";
            }
        });

        get("/logout", (req, res) -> {
            userService.removeFromWhitelist(req.queryParams("username"));
            return "index.html";
        });

        get("/candies", (req, res) -> {
            if (userService.isAuthenticated(req.queryParams("username"))) {
                return SecureURLReader.readExternalURL(getURL());
            } else {
                return "Unauthorized";
            }
        });
    }

    /**
     * Returns the port to use for the web application.
     * 
     * @return the port to use for the web application
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    /**
     * Returns the URL to access the secure external service.
     * 
     * @return the URL to access the secure external service
     */
    static String getURL() {
        if (System.getenv("URL") != null) {
            return System.getenv("URL");
        }
        return "https://localhost:6000/candies";
    }

}
