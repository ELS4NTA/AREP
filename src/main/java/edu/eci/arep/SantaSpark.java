package edu.eci.arep;

import java.util.HashMap;
import java.util.Map;

import edu.eci.arep.service.AppService;

/**
 * SantaSpark class to manage the routes of the server
 * 
 * @author Daniel Santanilla
 */
public class SantaSpark {

    private static SantaSpark instance;
    private final Map<String, AppService> getRoutes = new HashMap<>();
    private final Map<String, AppService> postRoutes = new HashMap<>();

    /**
     * Private constructor for the SantaSpark class.
     */
    private SantaSpark() {
    }

    /**
     * Method to get the instance of the SantaSpark class.
     * 
     * @return The instance of the SantaSpark class.
     */
    public static SantaSpark getInstance() {
        if (instance == null) {
            instance = new SantaSpark();
        }
        return instance;
    }

    /**
     * Method to set the static file location.
     * 
     * @param path The path of the static file location.
     */
    public static void staticFileLocation(String path) {
        HttpServer.getInstance().setStaticFileLocation(path);
    }

    /**
     * Method to define a GET route.
     * 
     * @param path    The path of the route.
     * @param handler The handler of the route.
     */
    public static void get(String path, AppService handler) {
        getInstance().getRoutes.put(path, handler);
    }

    /**
     * Method to define a POST route.
     * 
     * @param path    The path of the route.
     * @param handler The handler of the route.
     */
    public static void post(String path, AppService handler) {
        getInstance().postRoutes.put(path, handler);
    }

    /**
     * Method to find the handler of a route.
     * 
     * @param method The method of the route.
     * @param path   The path of the route.
     * @return The handler of the route.
     */
    public static AppService findHandler(String method, String path) {
        if ("GET".equalsIgnoreCase(method)) {
            return getInstance().getRoutes.get(path);
        } else if ("POST".equalsIgnoreCase(method)) {
            return getInstance().postRoutes.get(path);
        } else {
            // Handle case where there is no handler for the method
            return null;
        }
    }

}