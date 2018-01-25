package com.nisum.Client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.bo.Employee;

public class ParamsAsVariables {
	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {
		Response res = target.path("rest")
		.path("/listresponsewithid/{id}")
		.resolveTemplate("id", 38)
		//.matrixParam("role", "role")
		.queryParam("name", "My Name")
		.request()
		.accept(MediaType.APPLICATION_XML)
		.get();
		Employee emp = res.readEntity(Employee.class);
		System.out.println(emp.getId()+"::"+emp.getName()+"::"+emp.getRole());
		
	}
	
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}
}
