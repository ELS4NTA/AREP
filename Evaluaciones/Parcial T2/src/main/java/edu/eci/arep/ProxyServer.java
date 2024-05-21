package edu.eci.arep;

import static spark.Spark.*;

public class ProxyServer {

    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        get("/linearsearch", (req, res) -> RoundRobbin.getConnection(req, "/linearsearch"));
        get("/binarysearch", (req, res) -> RoundRobbin.getConnection(req, "/binarysearch"));
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

}
