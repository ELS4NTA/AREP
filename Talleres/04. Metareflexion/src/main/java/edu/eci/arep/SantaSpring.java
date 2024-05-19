package edu.eci.arep;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import edu.eci.arep.annotation.ComponentLoader;
import edu.eci.arep.annotation.HttpMethod;
import edu.eci.arep.annotation.RequestMapping;

/**
 * SantaSpring class to manage the routes of the server
 * 
 * @author Daniel Santanilla
 */
public class SantaSpring {

    private static SantaSpring instance;
    private ComponentLoader components;
    private final Map<String, Method> getRoutes = new HashMap<>();
    private final Map<String, Method> postRoutes = new HashMap<>();

    /**
     * Private constructor for the SantaSpark class.
     */
    private SantaSpring() {
        this.components = new ComponentLoader(App.class);
        saveMappings();
    }

    /**
     * Method to get the instance of the SantaSpark class.
     * 
     * @return The instance of the SantaSpark class.
     */
    public static SantaSpring getInstance() {
        if (instance == null) {
            instance = new SantaSpring();
        }
        return instance;
    }

    /**
     * Method to set the static file location.
     * 
     * @param path The path of the static file location.
     */
    private void saveMappings() {
        for (Class<?> cls : components.getClasscomponents()) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    HttpMethod httpMethod = method.getAnnotation(RequestMapping.class).method();
                    String path = method.getAnnotation(RequestMapping.class).path();
                    if (httpMethod.equals(HttpMethod.GET)) {
                        getRoutes.put(path, method);
                    } else if (httpMethod.equals(HttpMethod.POST)) {
                        postRoutes.put(path, method);
                    }
                }
            }
        }
    }

    /**
     * Method to find the mapping method.
     * 
     * @param path   The path of the route.
     * @param method The method of the route.
     * @return The method of the route.
     */
    public static Method findMappingMethod(String path, String method) {
        if ("GET".equalsIgnoreCase(method)) {
            return getInstance().getRoutes.get(path);
        } else if ("POST".equalsIgnoreCase(method)) {
            return getInstance().postRoutes.get(path);
        } else {
            // Handle case where there is no handler for the method
            return null;
        }
    }
    
}
