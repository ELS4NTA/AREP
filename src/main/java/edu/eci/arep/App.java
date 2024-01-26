package edu.eci.arep;

import java.io.IOException;

/**
 * App class to start the application
 * @author Daniel Santanilla
 */
public class App {
    /**
     * The main method of the application.
     *
     * @param args Command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        HttpServer server = new HttpServer(new HttpMovieConnection());
        try {
            server.start();
        } catch (IOException e) {
            System.err.println("Error en el servidor");
            System.exit(1);
        }
    }
}
