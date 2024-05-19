package edu.eci.arep.model;

/**
 * Represents a user in the system.
 */
public class User {

    private String username;
    private String password;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs a user with the given username.
     * 
     * @param username the username of the user
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * Constructs a user with the given username and password.
     * 
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}