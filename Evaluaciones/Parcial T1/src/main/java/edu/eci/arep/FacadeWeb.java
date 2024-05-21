package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

import com.google.gson.JsonObject;

public class FacadeWeb {

    public static void main(String[] args) {
        try {
            
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(35000);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000 " + e.getMessage());
                System.exit(1);
            }
    
            boolean running = true;
            while (running) {
                Socket clientSocket = null;
                try {
                    System.out.println("Facade ready to receive...");
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed " + e.getMessage());
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, outputLine;
        
                boolean firstLine = true;
                String requestStringURI = "";
        
        
                while ((inputLine = in.readLine()) != null) {
                    if (firstLine) {
                        System.out.println("Received: " + inputLine);
                        requestStringURI = inputLine.split(" ")[1];
                        firstLine = false;
                        continue;
                    }
                    if (!in.ready()) {
                        break;
                    }
                }
    
                URI requestURI = new URI(requestStringURI);
                
                if (requestURI.getPath().startsWith("/cliente")) {
                    outputLine = getClientResponse(requestURI);
                } else if (requestURI.getPath().startsWith("/consulta")) {
                    outputLine = getConsultResponse(requestURI);
                } else {
                    outputLine = getErrorPage();
                }
        
                out.println(outputLine);
                out.close();
                in.close();
                clientSocket.close();
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getConsultResponse(URI requestURI) throws IOException {
        JsonObject response = FacadeServices.getInstance().getReflectiveChatCommand(requestURI.getQuery());
        return "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "\n" +
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Consult</title>\n" +
                "</head>" +
                "<body>" +
                response.toString() +
                "</body>" +
                "</html>";
    }

    public static String getClientResponse(URI requestURI) {
        return "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html\n" +
                "\n" +
                "<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "    <head>\r\n" +
                "        <title>Reflective ChatGPT</title>\r\n" +
                "        <meta charset=\"UTF-8\">\r\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                "    </head>\r\n" +
                "    <body>\r\n" +
                "        <h1>Consult Reflective ChatGPT</h1>\r\n" +
                "        <form action=\"/consulta\">\r\n" +
                "            <label for=\"name\">Command:</label><br>\r\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\r\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n" +
                "        </form> \r\n" +
                "        <div id=\"getrespmsg\"></div>\r\n" +
                "\r\n" +
                "        <script>\r\n" +
                "            function loadGetMsg() {\r\n" +
                "                let nameVar = document.getElementById(\"name\").value;\r\n" +
                "                const xhttp = new XMLHttpRequest();\r\n" +
                "                xhttp.onload = function() {\r\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\r\n" +
                "                    this.responseText;\r\n" +
                "                }\r\n" +
                "                xhttp.open(\"GET\", \"/consulta?name=\"+nameVar);\r\n" +
                "                xhttp.send();\r\n" +
                "            }\r\n" +
                "        </script>\r\n" +
                "    </body>\r\n" +
                "</html>";
    }

    private static String getErrorPage() {
        return "HTTP/1.1 404 Not Found\n" +
                "Content-Type: text/html\n" +
                "\n" +
                "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Not Found</title>\n" +
                "</head>" +
                "<body>" +
                "<h1>404 Not Found</h1>" +
                "</body>" +
                "</html>";
    }

}
