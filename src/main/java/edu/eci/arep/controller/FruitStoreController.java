package edu.eci.arep.controller;

import com.google.gson.JsonObject;

import edu.eci.arep.annotation.Component;
import edu.eci.arep.annotation.HttpMethod;
import edu.eci.arep.annotation.RequestMapping;
import edu.eci.arep.service.FruitStoreService;

/**
 * FruitStoreController
 */
@Component
public class FruitStoreController {

    /**
     * Get the smoothies
     * @param calledServiceURI The URI of the called service
     * @return The smoothies in json format
     */
    @RequestMapping(path = "/smoothies", method = HttpMethod.GET)
    public static JsonObject getSmoothies(String calledServiceURI) {
        return FruitStoreService.getInstance().getSmoothies();
    }
    
}
