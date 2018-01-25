package com.nisum.Client;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.bo.Employee;

public class RestClient {

	static ClientConfig clientConfig = new ClientConfig();
	static Client client = ClientBuilder.newClient(clientConfig);
	static WebTarget target = client.target(getURI());
	public static void main(String[] args) {
		//System.out.println(target.path("employee").path("list").request().accept(MediaType.APPLICATION_XML).get(String.class));  
		/*System.out.println(target.path("employee")
			.path("/listresponse/{id}")
			.queryParam("name", "prash")
			.resolveTemplate("id", 37)
			.request()
			.accept(MediaType.APPLICATION_XML)
			.get(String.class));  */
/*
		System.out.println(target.path("employee")
				.path("/postemployee")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.post(Entity.entity(prepareEmployee(-18), MediaType.APPLICATION_JSON)));*/
		
		/*System.out.println(target.path("employee")
				.path("/postemployee")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.put(Entity.entity(prepareEmployee(19), MediaType.APPLICATION_JSON)));*/
		
		/*System.out.println(target.path("employee")
				.path("/postemployee")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.delete());*/

		System.out.println("Begin in Client");
		
		final Future<Response> responseFuture2 = target.path("web/employee")
				.path("/asynccall")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.async().get(new InvocationCallback<Response>() {
		            @Override
		            public void completed(Response response) {
		                System.out.println("Response status code "
		                        + response.getStatus() + " received.");
		            }
		 
		            @Override
		            public void failed(Throwable throwable) {
		                System.out.println("Invocation failed.");
		                throwable.printStackTrace();
		            }
		        })
				;
		try {
			System.out.println("Response:::"+responseFuture2.get().readEntity(Employee.class));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		target.path("employee")
				.path("/synccall")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		System.out.println("End in Client");
		
	}
	public static URI getURI() {
		return UriBuilder.fromUri("http://localhost:8080").build();
	}

	private static Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Sr Engineer");
		user.setName("Prashant N");
		user.setId(id);
		return user;
	}
	
	public static Future<Integer> userAsync() {
		 return target.path("employee")
				.path("/asynccall")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.async()
				.get(Integer.class);
		
		}
}
