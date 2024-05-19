package edu.eci.arep.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The UserServiceMap class represents a service that manages user
 * authentication and authorization using a map-based implementation.
 * It provides methods to validate user credentials, manage a whitelist of
 * authenticated users, and perform operations on the whitelist.
 * 
 * @author Daniel Santanilla
 */
public class UserServiceMap {

    private Map<String, String> users;
    private List<String> whitelist;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceMap.class);
    private static UserServiceMap instance;

    /**
     * Constructs a new UserServiceMap object with an empty map of users and an
     * empty list of whitelisted users.
     */
    private UserServiceMap() {
        users = new HashMap<>();
        whitelist = new ArrayList<>();
        users.put("Bob", hashOfPassword("password1"));
        users.put("Alice", hashOfPassword("password2"));
        users.put("Eve", hashOfPassword("password3"));
        users.put("Charlie", hashOfPassword("password4"));
    }

    /**
     * Returns the singleton instance of the UserServiceMap class.
     * 
     * @return the singleton instance of the UserServiceMap class
     */
    public static UserServiceMap getInstance() {
        if (instance == null) {
            instance = new UserServiceMap();
        }
        return instance;
    }

    /**
     * Returns the SHA-256 hash of the given password.
     * 
     * @param password the password to hash
     * @return the SHA-256 hash of the given password
     */
    private String hashOfPassword(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-256");
            byte[] hash = mda.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Error hashing password", e);
        }
        return null;
    }

    /**
     * Validates the given user credentials.
     * 
     * @param user     the user to validate
     * @param password the password to validate
     * @return true if the user credentials are valid, false otherwise
     */
    public boolean validateUser(String user, String password) {
        return users.containsKey(user) && users.get(user).equals(hashOfPassword(password));
    }

    /**
     * Returns true if the given user is authenticated, false otherwise.
     * 
     * @param user the user to check
     * @return true if the given user is authenticated, false otherwise
     */
    public boolean isAuthenticated(String user) {
        return whitelist.contains(user);
    }

    /**
     * Adds the given user to the whitelist of authenticated users.
     * 
     * @param user the user to add to the whitelist
     */
    public void addToWhitelist(String user) {
        whitelist.add(user);
    }

    /**
     * Removes the given user from the whitelist of authenticated users.
     * 
     * @param user the user to remove from the whitelist
     */
    public void removeFromWhitelist(String user) {
        whitelist.remove(user);
    }

}
