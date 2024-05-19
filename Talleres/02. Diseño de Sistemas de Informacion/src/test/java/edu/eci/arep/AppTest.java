package edu.eci.arep;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonObject;

/**
 * Unit test for simple HttpServer.
 */
public class AppTest {

    HttpServer server;

    @Before
    public void setUp() {
        server = new HttpServer(new HttpMovieConnection());
    }

    @Test
    public void shouldResponseHtmlFiles() {
        byte[] response = null;
        try {
            response = server.httpResponseBody(server.TEXT_HTML, server.BASE_URI + "/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldNotResponseFiles() {
        byte[] response = null;
        try {
            response = server.httpResponseBody(server.TEXT_HTML, server.BASE_URI + "/none.html");
        } catch (IOException e) {
            assertNull(response);
        }
    }

    @Test
    public void shouldResponseServeCssFiles() {
        byte[] response = null;
        try {
            response = server.httpResponseBody(server.TEXT_CSS, server.BASE_URI + "/css/styles.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldResponseServeJsFiles() {
        byte[] response = null;
        try {
            response = server.httpResponseBody(server.APPLICATION_JAVASCRIPT, server.BASE_URI + "/js/app.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldResponseServeImageFiles() {
        byte[] response = null;
        try {
            response = server.httpResponseBody(server.IMAGE_PNG, server.BASE_URI + "/img/favicon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shoulConnectToAPIAndReturnJSON () {
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
