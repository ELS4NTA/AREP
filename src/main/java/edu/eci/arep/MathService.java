package edu.eci.arep;

import static spark.Spark.get;
import static spark.Spark.port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class MathService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathService.class);

    public static void main(String... args) {
        port(getPort());
        get("/linearsearch", (req, res) -> {
            String[] inputlist = req.queryParams("list").split(",");
            String value = req.queryParams("value");
            return linearSearch(inputlist, value);
        });
        get("/binarysearch", (req, res) -> {
            String[] inputlist = req.queryParams("list").split(",");
            String value = req.queryParams("value");
            return binarySearch(inputlist, value);
        });
    }

    private static JsonObject linearSearch(String[] inputlist, String value) {
        LOGGER.info("Making linear search for inputlist: {}, value: {}", String.join(",", inputlist), value);
        JsonObject response = createJsonResponse("linearSearch", inputlist, value);
        for (int i = 0; i < inputlist.length; i++) {
            if (value.equals(inputlist[i])) {
                response.addProperty("output", String.valueOf(i));
                return response;
            }
        }
        response.addProperty("output", String.valueOf(-1));
        return response;
    }

    private static JsonObject binarySearch(String[] inputlist, String value) {
        LOGGER.info("Making binary search for inputlist: {}, value: {}", String.join(",", inputlist), value);
        JsonObject response = createJsonResponse("binarySearch", inputlist, value);
        int result = recursiveBinarySearch(inputlist, value, 0, inputlist.length - 1);
        response.addProperty("output", String.valueOf(result));
        return response;
    }

    private static int recursiveBinarySearch(String[] inputlist, String value, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (value.equals(inputlist[mid])) {
                return mid;
            }
            if (Integer.parseInt(inputlist[mid]) > Integer.parseInt(value)) {
                return recursiveBinarySearch(inputlist, value, left, mid - 1);
            }
            return recursiveBinarySearch(inputlist, value, mid + 1, right);
        }
        return -1;
    }

    private static JsonObject createJsonResponse(String operation, String[] inputlist, String value) {
        JsonObject response = new JsonObject();
        response.addProperty("operation", operation);
        response.addProperty("inputlist", String.join(",", inputlist));
        response.addProperty("value", value);
        return response;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
