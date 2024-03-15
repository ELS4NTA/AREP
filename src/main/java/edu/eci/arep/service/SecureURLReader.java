package edu.eci.arep.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SecureURLReader class represents a utility to read the content of a URL
 * using a secure connection.
 * 
 * @author Daniel Santanilla
 * @author Daniel Benavides
 */
public class SecureURLReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureURLReader.class);

    /**
     * Private constructor to avoid instantiation of this class.
     */
    private SecureURLReader() {
    }

    /**
     * Reads the content of the given URL using a secure connection.
     * 
     * @param url the URL to read
     * @return the content of the given URL
     */
    public static String readExternalURL(String url) {
        try {
            // Create a file and a password representation
            File trustStoreFile = new File(getTrustStorePath());
            char[] trustStorePassword = getTrustStorePassword().toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);

            // Print the trustManagers returned by the TMF only for debugging
            for (TrustManager t : tmf.getTrustManagers()) {
                LOGGER.info("TrustManager: {}", t.toString());
            }

            // Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException
                | KeyManagementException x) {
            LOGGER.error("Error loading truststore", x);
        }
        return readURL(url);
    }

    /**
     * Reads the content of the given URL.
     * 
     * @param sitetoread the URL to read
     * @return the content of the given URL
     */
    private static String readURL(String sitetoread) {
        String result = "";
        try {
            // Crea el objeto que representa una URL2
            URL siteURL = new URL(sitetoread);
            // Crea el objeto que URLConnection
            URLConnection urlConnection = siteURL.openConnection();
            // Obtiene los campos del encabezado y los almacena en un estructura Map
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            // Obtiene una vista del mapa como conjunto de pares <K,V>
            // para poder navegarlo
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            // Recorre la lista de campos e imprime los valores
            for (Map.Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();

                // Si el nombre es nulo, significa que es la linea de estado
                if (headerName != null) {
                    LOGGER.info("{}:", headerName);
                }
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    LOGGER.info("    {}", value);
                }
            }

            LOGGER.info("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                LOGGER.info(inputLine);
                result += inputLine;
            }
        } catch (IOException x) {
            LOGGER.error("Error reading URL", x);
        }
        return result;
    }

    /**
     * Returns the path of the trust store file.
     * 
     * @return the path of the trust store file
     */
    static String getTrustStorePath() {
        if (System.getenv("TSTORE") != null) {
            return System.getenv("TSTORE");
        }
        return "certificates/myTrustStore.p12";
    }

    /**
     * Returns the password of the trust store file.
     * 
     * @return the password of the trust store file
     */
    static String getTrustStorePassword() {
        if (System.getenv("TSPASS") != null) {
            return System.getenv("TSPASS");
        }
        return "654321";
    }

}