package com.nisum.RestServices;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.DAO.BasicRowMapper;
import com.bo.Employee;
import com.nisum.supporters.MyDate;

@Path("restversion")
public class VersionTest {
	int i=0;

	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	
	@Path("lifecycle")
	@GET

	public void getValue() {
		System.out.println("This method is called "+ ++i +" no of times");
	}
	
	@MatrixParam("role") private String role;
	@QueryParam("name") private String name;
	//@PathParam("id") private int id;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/v1/{id}")
	public Response getEmployeeById(@PathParam("id") Integer id) {

		Employee emp=empDao.getEmployeesUsingCustomResultSetRowMapper(id,name,role);
		GenericEntity<Employee> empData = new GenericEntity<Employee>(emp) {};
		return Response.status(200).entity("Response From V1").build();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/v2/{id}")
	public Response getEmployeeByEmpId(@PathParam("id") Integer id) {

		Employee emp=empDao.getEmployeesUsingCustomResultSetRowMapper(id,name,role);
		GenericEntity<Employee> empData = new GenericEntity<Employee>(emp) {};
		return Response.status(200).entity("Response From V2").build();
	}
	
	
}
