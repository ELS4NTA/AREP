package edu.eci.arep;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.google.gson.JsonObject;

/**
 * Cache class to store the movies
 * 
 * @author Daniel Santanilla
 */
public class Cache {

    private static Cache instance;
    private ConcurrentMap<String, JsonObject> data;

    private Cache() {
        this.data = new ConcurrentHashMap<>();
    }

    /**
     * Get the instance of the cache
     * 
     * @return the instance of the cache
     */
    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Add a new movie to the cache
     * 
     * @param movie the name of the movie
     * @param json  the json of the movie
     */
    public void add(String movie, JsonObject json) {
        data.putIfAbsent(movie, json);
    }

    /**
     * Get the json of a movie
     * 
     * @param movie the name of the movie
     * @return the json of the movie
     */
    public JsonObject get(String movie) {
        return data.get(movie);
    }

    /**
     * Check if the cache contains a movie
     * 
     * @param movie the name of the movie
     * @return true if the movie is in the cache, false otherwise
     */
    public boolean contains(String movie) {
        return data.containsKey(movie);
    }

}