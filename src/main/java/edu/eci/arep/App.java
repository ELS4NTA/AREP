package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

/**
 * This class represents the main application class.
 * It contains the entry point for the application and sets up the necessary
 * routes.
 * 
 * @author Daniel Santanilla
 */
public class App {

    /**
     * The main method of the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/log", (req, res) -> RRInvoker.invoke(req.queryParams("message")));
    }

    /**
     * Retrieves the port from the environment variable or uses the default port
     * 8080.
     *
     * @return the port to use
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

}
