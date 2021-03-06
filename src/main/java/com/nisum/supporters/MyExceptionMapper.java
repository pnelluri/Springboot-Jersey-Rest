package com.nisum.supporters;

 
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
 
@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException>{

	public Response toResponse(Exception ex)
	{
		return Response.status(Status.BAD_REQUEST)
				.entity(new ErrorProps("400", ex.getMessage()))
				.build();
	}
	public Response toResponse(MyException ex)
	{
		return Response.status(Status.BAD_REQUEST)
				.entity(new ErrorProps("400", ex.getMessage()))
				.build();
	}
}