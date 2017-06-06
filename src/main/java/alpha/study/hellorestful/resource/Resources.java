package alpha.study.hellorestful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import alpha.study.hellorestful.server.RESTfulServer;

/**
 * Root resource (exposed at "/" path)
 */
@Path("/")
public class Resources {
	private static final Logger LOGGER = LoggerFactory.getLogger(Resources.class);

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getResource() {
		return "GET root resource.\n";
	}

	@Path("/resource/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postResource(String data) {
		LOGGER.trace("Received: {}", data);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("POST resource: " + data + "\n").build();
	}

	@Path("/resource/")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putResource(String data) {
		LOGGER.trace("Received: {}", data);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("PUT resource: " + data + "\n").build();
	}

	@Path("/resource/{id:\\d+}/")
	@DELETE
	public Response deleteResource(@PathParam("id") int id) {
		LOGGER.trace("Received: {}", id);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("DELETE resource id: " + id + "\n").build();
	}

	@Path("/resource/{id:\\d+}/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResource(@PathParam("id") int id) {
		LOGGER.trace("Received: {}", id);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("GET resource id: " + id + "\n").build();
	}

	@Path("/resource/{begin:\\d+}-{end:\\d+}/")
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResource(@PathParam("begin") int begin, @PathParam("end") int end) {
		LOGGER.trace("Received: {} - {}", begin, end);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("GET resource id: " + begin + "-" + end + "\n").build();
	}

	@Path("/resource/{name}/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResource(@PathParam("name") String name) {
		LOGGER.trace("Received: {}", name);
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("GET resource name: " + name + "\n").build();
	}
	
	@Path("/stop/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postStop(String data) {
		LOGGER.trace("Received: {}", data);
		RESTfulServer.stopServer();
		int status = ((int)(Math.random() * 10000) % 5 + 1) * 100 + (int)(Math.random() * 10000) % 10;
		return Response.status(status).entity("POST stop: " + data + "\n").build();
	}
}
