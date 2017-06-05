package alpha.study.hellorestful.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.ProcessingException;

/**
 * Main class.
 *
 */
public class RESTfulServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTfulServer.class);

	// Base URI the Grizzly HTTP server will listen on
	private static final String hostPropertyName = "HOST";
	private static final String portPropertyName = "PORT";
	private static final String pathPropertyName = "PATH";
	private static final String defaultHost = "localhost";
	private static final String defaultPort = "9981";
	private static final String defaultPath = "/";
	private static final String baseURI = baseURI();

	// package of JAX-RS resources defined in this application
	private static final String resourcePackage = "alpha.study.hellorestful.resource";

	// Grizzly HTTP server exposing JAX-RS resources defined in this application
	private static HttpServer httpServer = null;

	// Base URI the Grizzly HTTP server will listen on
	public static String baseURI() {
		if (baseURI != null) return baseURI;
    	String host = System.getProperty(hostPropertyName, defaultHost);
    	String port = System.getProperty(portPropertyName, defaultPort);
    	String path = System.getProperty(pathPropertyName, defaultPath);
    	return "http://" + host + ":" + port + path;
	}

	/**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return true if success, otherwise false.
     */
    public static boolean startServer() {
    	if (httpServer != null) {
	        LOGGER.info("Grizzly HTTP server for Jersey RESTful Web Services "
	        		+ "have started with WADL available at {}application.wadl.", baseURI());
    		return true;
    	}

        try {
	        // create a resource config that scans for JAX-RS resources and providers
	        final ResourceConfig rc = new ResourceConfig().packages(resourcePackage);

	        // create and start a new instance of grizzly http server
	        // exposing the Jersey application at BASE_URI
	        httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseURI()), rc, true);
	
	        if (httpServer != null) {
		        LOGGER.info("succeed! Grizzly HTTP server for Jersey RESTful Web Services "
		        		+ "start with WADL available at {}application.wadl.", baseURI());
		        return true;
	        } else {
	        	LOGGER.error("fail! Grizzly HTTP server for Jersey RESTful Web Services "
	        			+ "start with WADL available at {}.", baseURI());
	        	return false;
	        }
        } catch (ProcessingException e) {
        	LOGGER.error("fail! Grizzly HTTP server for Jersey RESTful Web Services "
        			+ "start with WADL available at {}.\n{}", baseURI(), ExceptionUtils.getStackTrace(e));
		}
        return false;
    }

	/**
     * Stops Grizzly HTTP server exposing JAX-RS resources defined in this application.
     */
    public static void stopServer() {
        if (httpServer != null) {
	    	LOGGER.info("Stopping Grizzly HTTP server for Jersey RESTful Web Services at {} ...", baseURI());
	    	httpServer.shutdownNow();
	    	httpServer = null;
    	}
        LOGGER.info("Grizzly HTTP server for Jersey RESTful Web Services at {} have stopped.", baseURI());
    }

    /**
     * 
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        startServer();
        LOGGER.info("Hit enter to stop the application ...");
        System.in.read();
        stopServer();
    }
}
