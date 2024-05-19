package edu.eci.arep.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for component scan
 * @author Daniel Santanilla
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
    
    /**
     * Base package of the component scan
     * @return The base package of the component scan
     */
    String basePackage();

}
