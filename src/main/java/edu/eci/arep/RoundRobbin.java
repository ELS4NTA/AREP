package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;

public class RoundRobbin {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<String> DOMAINS = Arrays.asList("3.85.236.53:4567", "107.20.48.20:4567");
    private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobbin.class);
    private static int currentLogService = 0;

    private RoundRobbin() {
    }

    public static JsonObject getConnection(Request req, String oper) throws IOException {

        URL obj = getUrlByRoundRobbin(req, oper);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        LOGGER.info("GET Response Code :: {}", responseCode);

        StringBuilder response = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            LOGGER.info(response.toString());
        } else {
            LOGGER.error("GET request not worked");
        }
        LOGGER.info("GET DONE");
        return JsonParser.parseString(response.toString()).getAsJsonObject();
    }

    private static URL getUrlByRoundRobbin(Request req, String oper) throws MalformedURLException {
        LOGGER.info("RoundRobbin: {}", currentLogService);
        String domain = DOMAINS.get(currentLogService);
        String list = req.queryParams("list");
        String value = req.queryParams("value");
        currentLogService = (currentLogService + 1) % DOMAINS.size();
        return new URL("http://" + domain + oper + "?list=" + list + "&value=" + value);
    }
}
