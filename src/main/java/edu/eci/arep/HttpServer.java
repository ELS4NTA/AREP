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
 * 
 * @author Daniel Benavides
 * @author Daniel Satanilla
 */
public class HttpServer {

    HttpMovieConnection service;

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
     * 
     * @param uriStr the URI of the movie
     * @return the HTML of the movie
     * @throws IOException
     */
    private String movieHtml(String uriStr) throws IOException {
        JsonObject response = service.get(uriStr);
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\r\n"
                + "<html>\r\n"
                + "<head>\r\n"
                + "    <title>Movie</title>\r\n"
                + "    <meta charset=\"ISO-8859-1\">\r\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
                + "    <style>\r\n"
                + "        .card{box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);max-width:500px;\r\n"
                + "            margin:20px auto;padding:20px;border-radius:15px;\r\n"
                + "            display:flex;background:#fff;align-items:center;}\r\n"
                + "        .poster{width:150px;height:225px;margin-right:20px;border-radius:15px;}\r\n"
                + "        .details{flex:1;display:flex;flex-direction:column;\r\n"
                + "            justify-content:space-between;}\r\n"
                + "        .title{font-size:1.5em;margin-bottom:10px;}\r\n"
                + "        .info{margin-right:10px;display:flex;flex-direction:column}\r\n"
                + "        .plot{font-style:italic;margin-bottom:10px;}\r\n"
                + "    </style>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "    <div class=\"card\">\r\n"
                + "        <img src=\"" + response.get("Poster").getAsString() + "\" alt=\"Movie Poster\" class=\"poster\">\r\n"
                + "        <div class=\"details\">\r\n"
                + "            <h2 class=\"title\">" + response.get("Title").getAsString() + "</h2>\r\n"
                + "            <div class=\"info\">\r\n"
                + "                <span>Released: " + response.get("Released") + "</span>\r\n"
                + "                <span>Genre: " + response.get("Genre") + "</span>\r\n"
                + "                <span>Director: " + response.get("Director") + "</span>\r\n"
                + "                <span>Actors: " + response.get("Actors") + "</span>\r\n"
                + "                <span>Language: " + response.get("Language") + "</span>\r\n"
                + "            </div>\r\n"
                + "            <p class=\"plot\">" + response.get("Plot") + "</p>\r\n"
                + "                <a href=\"/\"><button>Limpiar</button></a>\r\n"
                + "            </article>\r\n"
                + "        </div>\r\n"
                + "    </body>\r\n"
                + "</html>";
        return outputLine;
    }

    /**
     * Get the HTML of the web page
     * 
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
