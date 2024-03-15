package edu.eci.arep.service;

import java.util.HashMap;
import java.util.Map;

/**
 * The CandyService class represents a service that provides a map of candies
 * and their quantities. It provides a method to get the map of candies.
 * 
 * @author Daniel Santanilla
 */
public class CandyService {

    private Map<String, Integer> candies;
    private static CandyService instance;

    /**
     * Constructs a new CandyService object with a map of candies and their
     * quantities.
     */
    private CandyService() {
        candies = new HashMap<>();
        candies.put("Chocolate", 10);
        candies.put("Gomitas", 20);
        candies.put("Chicles", 30);
        candies.put("Paletas", 40);
    }

    /**
     * Returns the singleton instance of the CandyService class.
     * 
     * @return the singleton instance of the CandyService class
     */
    public static CandyService getInstance() {
        if (instance == null) {
            instance = new CandyService();
        }
        return instance;
    }

    /**
     * Returns the map of candies and their quantities.
     * 
     * @return the map of candies and their quantities
     */
    public Map<String, Integer> getCandies() {
        return candies;
    }

}
