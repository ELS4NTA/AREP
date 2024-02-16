package edu.eci.arep.controller;

import com.google.gson.JsonObject;

import edu.eci.arep.annotation.Component;
import edu.eci.arep.annotation.HttpMethod;
import edu.eci.arep.annotation.RequestMapping;
import edu.eci.arep.service.FruitStoreService;

@Component
public class FruitStoreController {

    @RequestMapping(path = "/smoothies", method = HttpMethod.GET)
    public static JsonObject getSmoothies(String calledServiceURI) {
        return FruitStoreService.getInstance().getSmoothies();
    }
    
}
