package edu.eci.arep.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.eci.arep.model.User;
import edu.eci.arep.services.UserService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

/**
 * This class contains unit tests for the UserController class.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@QuarkusTest
class UserControllerTest {

    @Inject
    UserController userController;

    @InjectMock
    UserService userService;

    User user;

    /**
     * Sets up the test environment before each test case.
     * Initializes the 'user' object with sample data.
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("pepe");
        user.setPassword("p4$sw0rD");
    }

    /**
     * Test case for the getUsers method.
     * It verifies that the getUsers method returns the expected response and
     * entity.
     */
    @Test
    void getUsers() {
        // Arrange
        List<User> users = List.of(user);
        when(userService.getUsers()).thenReturn(users);
        // Act
        Response response = userController.getUsers();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(users, response.getEntity());
    }

}
