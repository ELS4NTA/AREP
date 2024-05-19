package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * This class represents the log service application class.
 * It contains the entry point for the load balancer and sets up the necessary
 * 
 * @author Daniel Santanilla
 */
public class LogService {

    /**
     * The main method of the application.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, res) -> saveAndGetLogs(req.queryParams("message")));
    }

    /**
     * Saves a log message and retrieves the latest 10 log entries.
     *
     * @param message the log message to save
     * @return the latest 10 log entries as a string
     */
    private static String saveAndGetLogs(String message) {
        MongoUtil mongoService = new MongoUtil();
        mongoService.addLog(message);
        return mongoService.getLogs().toString();
    }

    /**
     * Retrieves the port from the environment variable or uses the default port
     * 6000.
     *
     * @return the port to use
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }

}
