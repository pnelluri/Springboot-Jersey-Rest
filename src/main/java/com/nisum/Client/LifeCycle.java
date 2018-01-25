package com.nisum.Client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class LifeCycle {
	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {
		target.path("rest")
		.path("/lifecycle")
		.request()
		.accept(MediaType.APPLICATION_XML)
		.get();
		
	}
	
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}

}
