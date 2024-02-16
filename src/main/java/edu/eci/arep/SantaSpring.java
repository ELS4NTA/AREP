package edu.eci.arep;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import edu.eci.arep.annotation.ComponentLoader;
import edu.eci.arep.annotation.HttpMethod;
import edu.eci.arep.annotation.RequestMapping;

public class SantaSpring {

    private static SantaSpring instance;
    private ComponentLoader components;
    private final Map<String, Method> getRoutes = new HashMap<>();
    private final Map<String, Method> postRoutes = new HashMap<>();

    private SantaSpring() {
        this.components = new ComponentLoader(App.class);
        saveMappings();
    }

    public static SantaSpring getInstance() {
        if (instance == null) {
            instance = new SantaSpring();
        }
        return instance;
    }

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
