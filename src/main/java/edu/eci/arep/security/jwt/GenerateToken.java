package edu.eci.arep.security.jwt;

import java.util.Arrays;
import java.util.HashSet;

import org.bson.Document;
import org.eclipse.microprofile.jwt.Claims;

import edu.eci.arep.model.User;
import edu.eci.arep.services.UserService;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * This class represents a service for generating JWT tokens.
 * It provides a method for generating a JWT token.
 * 
 * @author Angie Mojica
 * @author Daniel Santanilla
 */
@ApplicationScoped
public class GenerateToken {

    @Inject
    UserService userService;

    /**
     * Generate JWT token
     * 
     * @param user the user object containing login credentials
     * @return a token if the login is successful, or null otherwise
     */
    public TokenDto createToken(User user) {
        if (userService.verifyPassword(user.getUsername(), user.getPassword())) {
            String token = Jwt.issuer("https://server.example.com")
                    .upn(user.getUsername())
                    .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                    .claim(Claims.birthdate.name(), "2001-07-13")
                    .sign();
            return new TokenDto(token);
        }
        return null;
    }

    /**
     * This class represents a DTO for the token.
     */
    public class TokenDto {
        
        private String token;

        /**
         * Constructs a new TokenDto object with the specified token.
         *
         * @param token the JWT token
         */
        public TokenDto(String token) {
            this.token = token;
        }

        /**
         * Returns the token.
         *
         * @return the token as a String.
         */
        public String getToken() {
            return token;
        }

        /**
         * Sets the token value.
         *
         * @param token the token value to be set
         */
        public void setToken(String token) {
            this.token = token;
        }

    }

}
