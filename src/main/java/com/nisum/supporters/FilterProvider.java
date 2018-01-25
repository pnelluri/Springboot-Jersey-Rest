package com.nisum.supporters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.nisum.RestServices.MessageBodyConvertService;
@Provider
@NameBindFilter
public class FilterProvider implements ContainerRequestFilter, ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println("Response ::"+responseContext.getHeaders());
		responseContext.getHeaders().add("X-Powered-By", "Prashant :-)");
		
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Filter Request ::"+requestContext.getHeaders());
//		final SecurityContext securityContext =requestContext.getSecurityContext();
//		if (securityContext == null ||!securityContext.isUserInRole("privileged")) {
//			requestContext.abortWith(Response
//					.status(Response.Status.UNAUTHORIZED)
//					.entity("User cannot access the resource.")
//					.build());};
	}

}
