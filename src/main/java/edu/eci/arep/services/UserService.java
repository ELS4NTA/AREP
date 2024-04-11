package edu.eci.arep.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import edu.eci.arep.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * This class represents a service for managing users.
 * It provides methods for retrieving users from a MongoDB database,
 * adding new users, and hashing user passwords.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@ApplicationScoped
public class UserService {

    @Inject
    MongoClient mongoClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    void init() {
        addUser(new User("angie", "An6ie02"));
        addUser(new User("daniel", "ELS4NTA"));
    }

    /**
     * Retrieves a list of users from the database.
     *
     * @return The list of users.
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setUsername(document.getString("username"));
                user.setPassword("************");
                users.add(user);
            }
        } finally {
            cursor.close();
        }
        LOGGER.info("Getting users...");
        return users;
    }

    /**
     * Adds a new user to the database.
     *
     * @param user the user to be added
     * @return the document representing the added user
     */
    public Document addUser(User user) {
        Document document = new Document()
                .append("username", user.getUsername())
                .append("password", hashOfPassword(user.getPassword()));
        LOGGER.info("Adding user: {}", document);
        getCollection().insertOne(document);
        return document;
    }

    /**
     * Verifies the password of the given user.
     *
     * @param username the username of the user
     * @param password the password to be verified
     * @return true if the password is correct, false otherwise
     */
    public boolean verifyPassword(String username, String password) {
        Document document = getCollection().find(new Document("username", username)).first();
        if (document != null) {
            String hashedPassword = document.getString("password");
            return hashedPassword.equals(hashOfPassword(password));
        }
        return false;
    }

    /**
     * Retrieves the MongoDB collection for users.
     *
     * @return the MongoDB collection for users
     */
    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("quarkustwitter").getCollection("users");
    }

    /**
     * Hashes the given password using the SHA-256 algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password as a Base64-encoded string
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

}
