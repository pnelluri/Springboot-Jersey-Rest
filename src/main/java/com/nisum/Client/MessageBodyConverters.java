package com.nisum.Client;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.bo.Employee;
import com.nisum.supporters.DateMessageBodyReader;
import com.nisum.supporters.MyDate;

public class MessageBodyConverters {
	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {
		
		System.out.println(target.path("rest45")
		.path("/datenew")
		.request()
		.accept(MediaType.TEXT_PLAIN)
		.get(new GenericType<Date>() {}));;
		
	}
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}
}
