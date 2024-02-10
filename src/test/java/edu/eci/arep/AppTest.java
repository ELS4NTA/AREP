package edu.eci.arep;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

import edu.eci.arep.service.HttpMovieConnection;

/**
 * Unit test for simple HttpServer.
 */
public class AppTest {

    HttpServer server;

    @Before
    public void setUp() {
        server = HttpServer.getInstance();
        server.setStaticFileLocation("target/classes/public");
    }

    @Test
    public void shouldResponseHtmlFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/index.html");
            response = server.httpResponseBody(server.TEXT_HTML, uri);
        } catch (IOException | URISyntaxException e ) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldNotResponseFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/none.html");
            response = server.httpResponseBody(server.TEXT_HTML, uri);
        } catch (IOException | URISyntaxException e ) {
            assertNull(response);
        }
    }

    @Test
    public void shouldResponseServeCssFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/css/styles.css");
            response = server.httpResponseBody(server.TEXT_CSS, uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldResponseServeJsFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/js/game.js");
            response = server.httpResponseBody(server.APPLICATION_JAVASCRIPT, uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shouldResponseServeImageFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/images/favicon.png");
            response = server.httpResponseBody(server.IMAGE_PNG, uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

    @Test
    public void shoulConnectToAPIAndReturnJSON() {
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