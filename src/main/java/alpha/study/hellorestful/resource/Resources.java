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
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Root resource (exposed at "/" path)
 */
@Path("/")
public class Resources {
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getRootResource() {
		return "root resource.";
	}

	@Path("/person/")
	@POST
	@Consumes("application/json")
	public Response createPerson(String person) {
		return Response.status(200).entity("post").build();
	}

	@Path("/person/")
	@PUT
	@Consumes("application/json")
	public Response updatePerson(String person) {
		return null;
	}

	@Path("/person/{id:\\d+}/")
	@DELETE
	public Response deletePerson(@PathParam("id") int id) {
		return null;
	}

	@Path("/person/{id:\\d+}/")
	@GET
	@Produces("application/json")
	public Response readPerson(@PathParam("id") int id) {
		return Response.status(200).entity("get " + id).build();
	}

	@Path("/person/{name}/")
	@GET
	@Produces("application/json")
	public Response readPersonByName(@PathParam("name") String name) {
		return null;
	}
}
