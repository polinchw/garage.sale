package com.hall.garage.sale.ws.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("version")
@RequestScoped
public class VersionService {
	
//	@Inject
	Logger log = Logger.getLogger(VersionService.class.getName());
	
	@GET
	@Produces("text/plain")
	public String getVersion() throws Exception {
		log.fine(VersionService.class.getClass().getPackage().getImplementationTitle());
		log.fine(VersionService.class.getClass().getPackage().getImplementationVersion());
		return VersionService.class.getClass().getPackage().getImplementationVersion();
	}

}
