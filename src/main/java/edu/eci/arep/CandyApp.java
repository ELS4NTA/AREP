package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.secure;

import com.google.gson.JsonParser;

import edu.eci.arep.service.CandyService;

/**
 * The CandyApp class represents a web application that provides a service to
 * get a map of candies and their quantities.
 * 
 * @author Daniel Santanilla
 */
public class CandyApp {

    /**
     * The main method of the web application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CandyService candyService = CandyService.getInstance();
        secure("certificates/ecikeystore.p12", "123456", null, null);
        port(getPort());
        get("/candies", (req, res) -> {
            return JsonParser.parseString(candyService.getCandies().toString()).getAsJsonObject();
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
        return 6000;
    }

}
