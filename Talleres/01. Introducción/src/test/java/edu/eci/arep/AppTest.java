package edu.eci.arep;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonObject;

public class AppTest {

    @Test
    public void shouldConnectToAPIAndReturnJSON () {
        HttpMovieConnection connection = new HttpMovieConnection();
        JsonObject response;
        try {
            response = connection.get("movie=Inception");
            assertNotNull(response);
            assertTrue(response.get("Title").getAsString().equals("Inception"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
