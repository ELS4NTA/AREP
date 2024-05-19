package edu.eci.arep;

import com.google.gson.JsonObject;

import edu.eci.arep.annotation.ComponentScan;
import edu.eci.arep.service.FruitStoreService;
import edu.eci.arep.service.HttpMovieConnection;

/**
 * App class to start the application
 * 
 * @author Daniel Santanilla
 */
@ComponentScan(basePackage = "edu.eci.arep")
public class App {

    /**
     * The main method of the application.
     * 
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Set the static file location
        HttpServer.getInstance().setStaticFileLocation("target/classes/public");

        // Load frameworks and services
        loadWithMySpark();
        loadWithMySpring();

        // Start the server
        try {
            if (!HttpServer.getInstance().isRunning())
                HttpServer.getInstance().start();
        } catch (Exception e) {
            System.err.println("Error in the server: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Load the services with the mini Spark framework.
     */
    private static void loadWithMySpark() {
        // Define the services
        SantaSpark.get("/movie", (requestURI) -> {
            HttpMovieConnection service = new HttpMovieConnection();
            JsonObject response = service.get(requestURI.getQuery());
            return response.toString().getBytes();
        });
        SantaSpark.post("/fruitstore", (requestURI) -> {
            FruitStoreService service = FruitStoreService.getInstance();
            JsonObject response = service.addSmoothie(requestURI.getQuery());
            return response.toString().getBytes();
        });
    }

    /**
     * Load the services with the mini Spring framework.
     */
    private static void loadWithMySpring() {
        // Load the SantaSpring context
        SantaSpring.getInstance();
    }
}