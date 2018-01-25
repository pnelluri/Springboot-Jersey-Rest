package com.nisum.Client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.bo.Employee;

public class GenericDemo {

	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {

		List<Employee> emp = target.path("employee").path("list").request().
				accept(MediaType.APPLICATION_XML).get(new GenericType<List<Employee>>() {});
		System.out.println(emp.size());

	}
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}

}
