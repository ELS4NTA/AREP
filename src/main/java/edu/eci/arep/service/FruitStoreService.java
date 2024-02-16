package edu.eci.arep.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * FruitStoreService class to manage the fruit store service
 * 
 * @author Daniel Santanilla
 */
public class FruitStoreService {

    private static FruitStoreService instance;
    private Map<String, JsonObject> smoothies;
    private Map<String, Integer> fruitPrices = new HashMap<>();

    private FruitStoreService() {
        this.smoothies = new ConcurrentHashMap<>();
        fruitPrices.put("apple", 2000);
        fruitPrices.put("banana", 1000);
        fruitPrices.put("cherry", 2500);
        fruitPrices.put("grape", 3000);
        fruitPrices.put("kiwi", 1500);
        fruitPrices.put("lemon", 2000);
        fruitPrices.put("mango", 4000);
        fruitPrices.put("mangosteen", 5000);
        fruitPrices.put("orange", 3000);
        fruitPrices.put("pear", 3000);
        fruitPrices.put("strawberry", 1500);
        fruitPrices.put("watermelon", 5000);
    }

    /**
     * Method to get the instance of the FruitStoreService class.
     * 
     * @return The instance of the FruitStoreService class.
     */
    public static FruitStoreService getInstance() {
        if (instance == null) {
            instance = new FruitStoreService();
        }
        return instance;
    }

    /**
     * Method to add a smoothie to the store.
     * 
     * @param URIStr The URI string.
     * @return The smoothie added to the store.
     */
    public JsonObject addSmoothie(String URIStr) {
        String name = URIStr.split("=")[1];
        JsonObject smoothie = makeRandomSmoothie();
        smoothie.addProperty("name", name);
        smoothies.put(name, smoothie);
        return smoothie;

    }

    /**
     * Method to get the smoothies from the store.
     * 
     * @return The smoothies from the store.
     */
    public JsonObject getSmoothies() {
        // an object of list of smoothies
        JsonObject response = new JsonObject();
        JsonArray smoothiesArray = new JsonArray();
        for (String key : smoothies.keySet()) {
            smoothiesArray.add(smoothies.get(key));
        }
        response.add("smoothies", smoothiesArray);
        return response;
    }

    /**
     * Method to make a random smoothie.
     * 
     * @return The random smoothie.
     */
    private JsonObject makeRandomSmoothie() {
        JsonObject smoothie = new JsonObject();
        JsonArray fruits = new JsonArray();
        int numFruits = (int) (Math.random() * 9) + 1;
        int price = 0;
        for (int i = 0; i < numFruits; i++) {
            int fruitIndex = (int) (Math.random() * fruitPrices.size());
            String fruit = (String) fruitPrices.keySet().toArray()[fruitIndex];
            int fruitPrice = fruitPrices.get(fruit);
            price += fruitPrice;
            fruits.add(fruit);
        }
        smoothie.addProperty("price", price);
        smoothie.add("fruits", fruits);
        return smoothie;
    }

}