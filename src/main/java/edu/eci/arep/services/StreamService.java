package edu.eci.arep.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import edu.eci.arep.model.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * This class represents a service for managing streams of posts.
 * It provides methods for retrieving posts from a MongoDB collection
 * and adding new posts to the collection.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@ApplicationScoped
public class StreamService {

    @Inject
    MongoClient mongoClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamService.class);

    /**
     * Retrieves the stream of posts from the database.
     *
     * @return a list of Post objects representing the stream of posts.
     */
    public List<Post> getStream() {
        List<Post> posts = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Post post = new Post();
                post.setId(document.getString("id"));
                post.setUsername(document.getString("creator"));
                post.setCreationDate(
                        document.getDate("creationDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                post.setContent(document.getString("content"));
                posts.add(post);
            }
        } finally {
            cursor.close();
        }
        LOGGER.info("Getting posts...");
        return posts;
    }

    /**
     * Adds a post to the stream and returns the created document.
     *
     * @param post the post to be added
     * @return the created document
     */
    public Document addPost(Post post) {
        String id = UUID.randomUUID().toString();
        post.setId(id);
        Document document = new Document()
                .append("id", post.getId())
                .append("creator", post.getUsername())
                .append("creationDate", LocalDate.now())
                .append("content", post.getContent());
        LOGGER.info("Adding post to stream: {} -- {}", post.getId(), post.getUsername());
        getCollection().insertOne(document);
        return document;
    }

    /**
     * Retrieves the MongoDB collection used for storing stream data.
     *
     * @return The MongoDB collection object.
     */
    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("quarkustwitter").getCollection("stream");
    }

}
