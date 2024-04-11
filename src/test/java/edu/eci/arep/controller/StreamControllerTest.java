package edu.eci.arep.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.arep.model.Post;
import edu.eci.arep.services.StreamService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

/**
 * This class contains unit tests for the StreamController class.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@QuarkusTest
class StreamControllerTest {

    @Inject
    StreamController streamController;

    @InjectMock
    StreamService streamService;

    Post post;

    /**
     * Sets up the test environment before each test case.
     * Initializes the 'post' object with sample data.
     */
    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId("1");
        post.setUsername("user");
        post.setCreationDate(LocalDate.now());
        post.setContent("content");
    }

    /**
     * This test verifies that the getPosts method returns the expected response
     * status code and entity.
     */
    @Test
    void getPosts() {
        // Arrange
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(streamService.getStream()).thenReturn(posts);
        // Act
        Response response = streamController.getPosts();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(posts, response.getEntity());
    }

    /**
     * Test case for creating a post.
     */
    @Test
    void createPost() {
        // Arrange
        Document document = new Document()
                .append("id", post.getId())
                .append("creator", post.getUsername())
                .append("creationDate", post.getCreationDate())
                .append("content", post.getContent());
        when(streamService.addPost(post)).thenReturn(document);
        // Act
        Response response = streamController.createPost(post);
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(document, response.getEntity());
    }

}
