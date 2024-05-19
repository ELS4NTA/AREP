package edu.eci.arep.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  Annotation for mapping web requests
 * @author Daniel Santanilla
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {

    /**
     * Path of the request
     * @return The path of the request
     */
    String path();

    /**
     * Http Method of the request
     * @return The method of the request
     */
    HttpMethod method();

}
