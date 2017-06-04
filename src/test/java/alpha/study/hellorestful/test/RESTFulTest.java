package alpha.study.hellorestful.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alpha.study.hellorestful.server.RESTfulServer;

import static org.junit.Assert.assertEquals;

public class RESTFulTest {
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        RESTfulServer.startServer();
        // create the client
        Client client = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // client.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = client.target(RESTfulServer.baseURI());
    }

    @After
    public void tearDown() throws Exception {
        RESTfulServer.stopServer();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetRootResource() {
        String responseMsg = target.path("/").request().get(String.class);
        System.out.println(responseMsg);
        // assertEquals("Got it!\n", responseMsg);
    }
}
