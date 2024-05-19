package edu.eci.arep;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * App class to start the application
 * 
 * @author Daniel Santanilla
 */
public class App {

    private static final String VALUE = "value";

     /**
     * The main method of the application.
     * 
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String... args) {
        // Detect all exceptions and print the stack trace.
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        // Set the location of the static files.
        staticFiles.location("/public"); 
        port(getPort());


        get("/sin", (req, res) -> CalculatorService.calculateSin(Double.parseDouble(req.queryParams(VALUE))));
        get("/cos", (req, res) -> CalculatorService.calculateCos(Double.parseDouble(req.queryParams(VALUE))));
        get("/palindrome", (req, res) -> CalculatorService.isPalindrome(req.queryParams(VALUE)));
        get("/vector", (req, res) -> {
            List<Double> vector = new ArrayList<>();
            for (String param : req.queryParams()) {
                vector.add(Double.parseDouble(req.queryParams(param)));
            }
            double[] vectorArray = vector.stream().mapToDouble(Double::doubleValue).toArray();
            return CalculatorService.vectorMagnitude(vectorArray);
        });

    }

    /**
     * Get the port from the environment or return default port.
     * 
     * @return The port to use.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
