package edu.eci.arep.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;

import edu.eci.arep.model.User;
import edu.eci.arep.security.jwt.GenerateToken;
import edu.eci.arep.security.jwt.GenerateToken.TokenDto;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * This class represents a resource that is secured by a token.
 * It provides endpoints for user login and token generation.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@Path("/secured")
@RequestScoped
public class TokenSecuredResource {

    @Inject
    JsonWebToken jwt;
    @Inject
    GenerateToken tokenService;

    /**
     * Logs in the user and returns a response containing a token.
     *
     * @param user the user object containing login credentials
     * @return a response containing a token if the login is successful, or an
     *         unauthorized response otherwise
     */
    @POST
    @Path("login")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        TokenDto token = tokenService.createToken(user);
        if (token != null) {
            return Response.ok(token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
