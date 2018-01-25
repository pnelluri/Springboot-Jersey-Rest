package com.nisum.RestServices;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;

import com.DAO.BasicJdbcTemplate;
import com.DAO.BasicPreparedStatement;
import com.DAO.BasicResultSetExtractor;
import com.DAO.BasicRowMapper;
import com.bo.Employee;
import com.nisum.supporters.MyException;

@Path("/employee")
public class RestService {
	int i = 0;
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	@Autowired
	private BasicJdbcTemplate basicEmpDao;

	@Autowired
	@Qualifier("bps")
	private BasicPreparedStatement basicPSEmpDao;
	@Autowired
	private BasicResultSetExtractor  basicRSEEmpDao;

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("list")
	public List<Employee> getEmployeesAsList() {
		return empDao.getEmployeesUsingResultSetRowMapper();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/listresponse")
	public Response getEmployeesAsresponse() {
		List<Employee> list=empDao.getEmployeesUsingResultSetRowMapper();
		GenericEntity<List<Employee>> list2 = new GenericEntity<List<Employee>>(list) {};
		return Response.status(200).entity(list2).build();
	}



	/*@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/listresponsewithid/{id}")
	public Response getEmployeeById(@PathParam("id") Integer id,@QueryParam("name") String name,@MatrixParam("role") String role) {

		Employee list=empDao.getEmployeesUsingCustomResultSetRowMapper(id,name,role);
		GenericEntity<Employee> list2 = new GenericEntity<Employee>(list) {};
		return Response.status(200).entity(list2).build();
	}*/
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/listresponsewithid/{id}")
	public Response getEmployeeById(@PathParam("id") Integer id,@MatrixParam("name") String name,@MatrixParam("role") String role) {

		Employee list=empDao.getEmployeesUsingCustomResultSetRowMapper(id,name,role);
		GenericEntity<Employee> list2 = new GenericEntity<Employee>(list) {};
		return Response.status(200).entity(list2).build();
	}
	@POST
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("postemployee")
	public Response saveEmployee(Employee e) {
		if(e.getId()<0) {
			throw new MyException("Id should be greater than 0");
		}
		int id = basicEmpDao.saveEmployee(e);
		return Response.status(200).entity(id).build();

	}

	@PUT
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("postemployee")
	public Response updateEmployee(Employee e) {
		if(e.getId()<0) {
			throw new MyException("Id should be greater than 0");
		}
		int id = basicEmpDao.updateEmployee(e);
		return Response.status(200).entity("Updated Successfully").build();

	}


	@DELETE
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("postemployee")
	public Response deleteEmployee(Employee e) {
		if(e.getId()<0) {
			throw new MyException("Id should be greater than 0");
		}
		int id = basicEmpDao.deleteEmployee(e);
		return Response.status(200).entity("Delete Successfully").build();

	}

	@Path("asynccall")
	@GET
	@Async
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
		System.out.println("Begin Async call");
		/*asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
         asyncResponse.setTimeoutHandler(ar -> ar.resume(
                 Response.status(Response.Status.SERVICE_UNAVAILABLE)
                         .entity("Operation timed out")
                         .build()));
		 */
		Employee result = prepareEmployee(10);
		basicEmpDao.updateEmployee(result);
		System.out.println("End Async call");
		System.out.println("End Async call after result");
		asyncResponse.resume(Response.status(200).entity(result).build());

	}

	private static Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Sr Engineer");
		user.setName("Prashant N");
		user.setId(id);
		return user;
	}
	@Path("synccall")
	@GET

	public void asyncGet() {
		System.out.println("Begin sync call");
		/*asyncResponse.setTimeout(1000, TimeUnit.MILLISECONDS);
        asyncResponse.setTimeoutHandler(ar -> ar.resume(
                Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Operation timed out")
                        .build()));
		 */
		int result = iterate2();
		System.out.println("End sync call");
		System.out.println("End ssync call after result");

	}

	public int iterate2() {
		int i=0;
		for(;i<10000;i++) {
			if(i==0)
				System.out.println("Begin of loop sync");
			if(i==999)
				System.out.println("End of loop sync");
		}
		return i;
	}

	public int iterate() {
		int i=0;
		for(;i<10000;i++) {
			if(i==0)
				System.out.println("Begin of loop async");
			if(i==999)
				System.out.println("End of loop async");
		}
		return i;
	}
	
}
