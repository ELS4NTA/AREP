package edu.eci.arep.service;

import java.net.URI;
import java.io.IOException;

@FunctionalInterface
public interface AppService {

    public byte[] handle(URI requestURI) throws IOException;

}
