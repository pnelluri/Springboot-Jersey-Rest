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
import com.nisum.supporters.MyDate;

public class ParamConverters {
	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {
		Response res = target.path("rest3")
		.path("/date/{dateString}")
		.resolveTemplate("dateString", "yesterday")
		.request()
		.accept(MediaType.TEXT_PLAIN)
		.get();
		String date = res.readEntity(String.class);
		System.out.println(date);
		
	}
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}
}
