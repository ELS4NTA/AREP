package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.JsonObject;

/**
 * HttpServer class to create a server to connect to the API of the movies
 * and show the information of the movie in a web page
 * @author Daniel Benavides
 * @author Daniel Satanilla
 */
public class HttpServer {

    HttpMovieConnection service;

    /**
     * Constructor of the class
     * @param service the service to connect to the API of the movies
     */
    public HttpServer(HttpMovieConnection service) {
        this.service = service;
    }

    /**
     * Start the server
     * @throws IOException if the connection fails
     */
    public void start() throws IOException {
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
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean firtLine = true;
            String uriStr = "";
            while ((inputLine = in.readLine()) != null) {
                if (firtLine) {
                    uriStr = inputLine.split(" ")[1];
                    firtLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (uriStr.startsWith("/movie")) {
                outputLine = movieHtml(uriStr);
            } else {
                outputLine = obtainHtml();
            }
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    /**
     * Get the HTML of the movie
     * @param uriStr the URI of the movie
     * @return the HTML of the movie
     * @throws IOException
     */
    private String movieHtml(String uriStr) throws IOException {
        JsonObject response = service.get(uriStr);
        String outputLine = "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\r\n"
                + "<html>\r\n"
                + "    <head>\r\n"
                + "        <title>Movie</title>\r\n"
                + "        <meta charset=\"ISO-8859-1\">\r\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                + "    </head>\r\n"
                + "    <body>\r\n"
                + "        <h1> Title:"+response.get("Title")+"</h1>\r\n"
                + "        <h2> Released:"+response.get("Released")+"</h2>\r\n"
                + "        <img src=>\""+response.get("Poster")+"\"/>\r\n"
                + "        <h2> Genre:"+response.get("Genre")+"</h2>\r\n"
                + "        <h2> Director:"+response.get("Director")+"</h2>\r\n"
                + "        <h2> Actors:"+response.get("Actors")+"</h2>\r\n"
                + "        <h2> Language:"+response.get("Language")+"</h2>\r\n"
                + "        <p> Plot:"+response.get("Plot")+"</p>\r\n"
                + "        <a href=\"/\"><button>Limpiar</button></a>\r\n"
                + "    </body>\r\n"
                + "</html>";
        return outputLine;
    }

    /**
     * Get the HTML of the web page
     * @return the HTML of the web page
     */
    private String obtainHtml() {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\r\n"
                + "<html>\r\n"
                + "    <head>\r\n"
                + "        <title>Movie Explorer</title>\r\n"
                + "        <meta charset=\"ISO-8859-1\">\r\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                + "        <style>body{display:flex;flex-direction:column;flex-wrap: wrap;align-content:center;"
                + "        background:rgba(0,212,255);}</style>\r\n"
                + "    </head>\r\n"
                + "    <body>\r\n"
                + "        <h1>Movie Expoler</h1>\r\n"
                + "        <form action=\"/movie\">\r\n"
                + "            <label for=\"name\">Movie Name:</label><br>\r\n"
                + "            <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"Insert your movie name here\"><br><br>\r\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n"
                + "        </form> \r\n"
                + "        <div id=\"getrespmsg\"></div>\r\n"
                + "        <script>\r\n"
                + "            function loadGetMsg() {\r\n"
                + "                let nameVar = document.getElementById(\"name\").value;\r\n"
                + "                const xhttp = new XMLHttpRequest();\r\n"
                + "                xhttp.onload = function() {\r\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\r\n"
                + "                    this.responseText;\r\n"
                + "                }\r\n"
                + "                xhttp.open(\"GET\", \"/movie?name=\"+nameVar);\r\n"
                + "                xhttp.send();\r\n"
                + "            }\r\n"
                + "        </script>\r\n"
                + "    </body>\r\n"
                + "</html>";
        return outputLine;
    }
}
