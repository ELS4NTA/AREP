package edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

public class ReflectiveChatWeb {

    public static void main(String[] args) throws IOException, URISyntaxException, NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(45000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 45000 " + e.getMessage());
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Reflective Chat ready to receive...");
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
            System.out.println(requestURI.toString());
            JsonObject response = makeReflection(requestURI.getQuery().split("=")[1]);
            outputLine = "HTTP/1.1 200 OK\r\n" + 
                         "Content-Type: application/json\r\n" + 
                         "\r\n" +
                         response.toString();
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static JsonObject makeReflection(String query) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JsonObject response = new JsonObject();
        String reflectiveMethod = query.split("\\(")[0];
        String params = query.split("\\(")[1].split("\\)")[0];
        String[] paramsArray = params.split(",");
        Class<?> clazz = null;
        String stringMethodName = "";
        
        clazz = Class.forName(paramsArray[0]);
        if (paramsArray.length > 1) {
            stringMethodName = paramsArray[1];
        }

        if (reflectiveMethod.equals("class")) {
            response = getDeclaredFieldsAndMethods(clazz);
        } else if (reflectiveMethod.equals("invoke")) {
            Class<?>[] offeredTypesArray = {};
            Method method = clazz.getMethod(stringMethodName, offeredTypesArray);
            response = invokingMethod(method);
        }
        return response;
    }
    
    private static JsonObject invokingMethod(Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        JsonObject response = new JsonObject();
        Object result = method.invoke(null, null);
        response.addProperty("result", result.toString());
        return response;
    }

    private static JsonObject getDeclaredFieldsAndMethods(Class<?> clazz) {
        JsonObject response = new JsonObject();
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        List<String> methodsList = new ArrayList<>();
        List<String> fieldsList = new ArrayList<>();
        for (Method method : methods) {
            methodsList.add(method.getName());
        }
        for (Field field : fields) {
            fieldsList.add(field.getName());
        }
        response.addProperty("methods", methodsList.toString());
        response.addProperty("fields", fieldsList.toString());
        return response;
    }

}
