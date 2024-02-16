package edu.eci.arep.service;

import java.net.URI;
import java.io.IOException;

/**
 * AppService for handling the request in minispark
 */
@FunctionalInterface
public interface AppService {

    /**
     * Handle the request
     * @param requestURI The URI of the request
     * @return The response of the request
     * @throws IOException If an error occurs
     */
    public byte[] handle(URI requestURI) throws IOException;

}