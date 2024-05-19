package edu.eci.arep.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;

/**
 * Represents a post made by a user.
 */
public class Post {

    private String id;
    private String username;
    private LocalDate creationDate;
    @Size(max = 140)
    private String content;

    /**
     * Default constructor for the Post class.
     */
    public Post() {
    }

    /**
     * Constructor for the Post class.
     * 
     * @param username     the username of the user who made the post
     * @param creationDate the creation date of the post
     * @param content      the content of the post
     */
    public Post(String username, LocalDate creationDate, String content) {
        this.username = username;
        this.creationDate = creationDate;
        this.content = content;
    }

    /**
     * Gets the ID of the post.
     * 
     * @return the ID of the post
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the post.
     * 
     * @param id the ID of the post
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the username of the user who made the post.
     * 
     * @return the username of the user who made the post
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user who made the post.
     * 
     * @param username the username of the user who made the post
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the creation date of the post.
     * 
     * @return the creation date of the post
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the post.
     * 
     * @param creationDate the creation date of the post
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the content of the post.
     * 
     * @return the content of the post
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     * 
     * @param content the content of the post
     */
    public void setContent(String content) {
        this.content = content;
    }

}
