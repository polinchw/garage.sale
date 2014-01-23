package com.hall.garage.sale.ws.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("version")
@RequestScoped
public class VersionService {
	
	@GET
	@Produces("text/xml")
	public String getVersion() throws Exception {
		return VersionService.class.getClass().getPackage().getImplementationVersion();
	}

}
