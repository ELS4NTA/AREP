package edu.eci.arep;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.google.gson.JsonObject;

/**
 * HttpServer class to start the server
 * 
 * @author Daniel Benavides
 * @author Daniel Satanilla
 */
public class HttpServer {

    public static final String BASE_URI = "target/classes/public";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_CSS = "text/css";
    public static final String APPLICATION_JAVASCRIPT = "application/javascript";
    public static final String IMAGE_PNG = "image/png";
    private HttpMovieConnection service;

    /**
     * Constructor of the class
     * 
     * @param service the service to connect to the API of the movies
     */
    public HttpServer(HttpMovieConnection service) {
        this.service = service;
    }

    /**
     * Start the server
     * 
     * @throws IOException if the connection fails
     */
    public void start() throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("\nListo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firstLine = true;
            String uriStr = "";

            while ((inputLine = in.readLine()) != null) {
                if (firstLine) {
                    System.out.println("Received: " + inputLine);
                    uriStr = inputLine.split(" ")[1];
                    firstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }

            URI resourceURI = new URI(uriStr);

            byte[] responseBody;

            try {
                if (resourceURI.getQuery() != null) {
                    System.out.println("path: " + resourceURI.getQuery());
                    JsonObject response = service.get(resourceURI.getQuery());
                    responseBody = response.toString().getBytes();
                    outputLine = httpResponseHeader(TEXT_PLAIN, responseBody);
                } else {
                    String path = BASE_URI + resourceURI.getPath();
                    String contentType = determineContentType(path);
                    responseBody = httpResponseBody(contentType, path);
                    outputLine = httpResponseHeader(contentType, responseBody);
                }
            } catch (IOException e) {
                responseBody = httpResponseBody(TEXT_HTML, BASE_URI + "/404.html");
                outputLine = httpResponseHeader(TEXT_HTML, responseBody);
            }

            try (OutputStream os = clientSocket.getOutputStream()) {
                os.write(outputLine.getBytes());
                os.write(responseBody);
            } catch (IOException e) {
                System.out.println("Error sending response body");
            }

            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Determine the content type of the file
     * 
     * @param path the path of the file
     * @return the content type of the file
     */
    private String determineContentType(String path) {
        String contentType = TEXT_PLAIN;
        if (path.endsWith(".html")) {
            contentType = TEXT_HTML;
        } else if (path.endsWith(".css")) {
            contentType = TEXT_CSS;
        } else if (path.endsWith(".js")) {
            contentType = APPLICATION_JAVASCRIPT;
        } else if (path.endsWith(".png")) {
            contentType = IMAGE_PNG;
        }
        return contentType;
    }

    /**
     * Get the response body of the request
     * 
     * @param contentType the content type of the file
     * @param path        the path of the file
     * @return the response in bytes
     * @throws IOException if the file is not found
     */
    public byte[] httpResponseBody(String contentType, String path) throws IOException {
        if (contentType.startsWith("image")) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(ImageIO.read(new File(path)), contentType.split("/")[1], baos);
            return baos.toByteArray();
        }
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * Get the response header of the request
     * 
     * @param contentType the content type of the file
     * @param fileContent the content of the file
     * @return the response header
     */
    private String httpResponseHeader(String contentType, byte[] fileContent) {
        return "HTTP/1.1 200 OK\r\n"
                + "Accept-Ranges: bytes\r\n"
                + "Server: Santa's Java HTTP Server\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + fileContent.length + "\r\n"
                + "\r\n";
    }

}
