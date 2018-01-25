package com.nisum.RestServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nisum.supporters.MyDate;

@Path("rest3")
public class ParamConvertService {
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/date/{dateString}")
	public Response getDate(@PathParam("dateString") MyDate id) {
		return Response.status(200).entity("Got" + id.toString()).build();
	}
}
