package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * HttpMovieConnection class to connect to the API of the movies
 * 
 * @author Daniel Benavides
 * @author Daniel Satanilla
 */
public class HttpMovieConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://www.omdbapi.com/?apikey=835ebafd&t=";

    /**
     * Get the JSON object of the movie
     * 
     * @param URIStr the URI of the movie
     * @return the JSON object of the movie
     * @throws IOException if the connection fails
     */
    public JsonObject get(String URIStr) throws IOException {
        String movie = URIStr.split("=")[1];
        if (Cache.getInstance().contains(movie)) {
            System.out.println("The movie is in the cache");
            return Cache.getInstance().get(movie);
        }

        URL obj = new URL(GET_URL + movie);
        System.out.println("GET URL: " + obj.toString());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            Cache.getInstance().add(movie, JsonParser.parseString(response.toString()).getAsJsonObject());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return Cache.getInstance().get(movie);
    }

}