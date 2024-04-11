package edu.eci.arep.controller;

import edu.eci.arep.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * This class represents the controller for managing user-related operations.
 * It handles HTTP requests related to users and interacts with the UserService.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@Path("api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    /**
     * Retrieves the users and returns a Response object.
     *
     * @return a Response object containing the users.
     */
    @GET
    public Response getUsers() {
        return Response.ok(userService.getUsers()).build();
    }

}
