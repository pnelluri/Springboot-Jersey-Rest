package com.nisum.RestServices;


import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nisum.supporters.MyDate;
import com.nisum.supporters.NameBindFilter;

@Path("rest45")
public class MessageBodyConvertService {
	@GET
	@Produces({MediaType.TEXT_PLAIN,"text/shortdate"})
	@Path("/datenew")
	public Date getDate() {
		System.out.println("in getDate");
		return  Calendar.getInstance().getTime();
	}
	
	@GET
	@Produces({MediaType.TEXT_PLAIN,"text/shortdate"})
	@Path("/datenewbind")
	@NameBindFilter
	public Date getDateBind() {
		System.out.println("in getDateBind");
		return  Calendar.getInstance().getTime();
	}
	
	@GET
	@Produces({MediaType.TEXT_PLAIN,"text/shortdate"})
	@Path("/datenewdynamicbind")
	public Date getDateDynamicBind() {
		System.out.println("in getDateDynamicBind");
		return  Calendar.getInstance().getTime();
	}
}
