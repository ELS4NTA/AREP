package edu.eci.arep.annotation;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ComponentLoader {

    private static final List<Class<?>> classComponents = new ArrayList<>();

    public ComponentLoader(Class<?> annotatedClass) {
        try {
            if (annotatedClass.isAnnotationPresent(ComponentScan.class)) {
                ComponentScan componentScan = annotatedClass.getAnnotation(ComponentScan.class);
                String basePackage = componentScan.basePackage();
                scanPackage(basePackage);
            }
        } 
        catch (Exception e) {
            System.err.println("Error trying to load the components: " + e.getMessage());
            System.exit(1);
        }
    }

    private void scanPackage(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        for (File directory : dirs) {
            findComponents(directory, packageName);
        }
    }

    private void findComponents(File directory, String packageName) throws Exception {
        if (!directory.exists()) return;
        File[] files = directory.listFiles();
        for (File file : files) {
            // Call recursively to search for classes in the subdirectory.
            if (file.isDirectory()) {
                findComponents(file, packageName + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                // Remove the .class extension and load the className.
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                Class<?> cls = Class.forName(className);
                if (cls.isAnnotationPresent(Component.class)) {
                    classComponents.add(cls);
                }
            }
        }
    }

    public List<Class<?>> getClasscomponents() {
        return classComponents;
    }
}
