package edu.eci.arep;

import com.google.gson.JsonObject;

import edu.eci.arep.service.HttpMovieConnection;
import edu.eci.arep.service.FruitStoreService;

/**
 * App class to start the application
 * 
 * @author Daniel Santanilla
 */
public class App {

    /**
     * The main method of the application.
     * 
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Set the static file location
        SantaSpark.staticFileLocation("target/classes/public");

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

        // Start the server
        try {
            if (!HttpServer.getInstance().isRunning())
                HttpServer.getInstance().start();
        } catch (Exception e) {
            System.err.println("Error en el servidor");
            System.exit(1);
        }
    }

}