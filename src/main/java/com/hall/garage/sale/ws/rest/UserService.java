package com.hall.garage.sale.ws.rest;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.security.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import com.hall.garage.sale.model.User;
import com.hall.garage.sale.service.UserEjb;

/**
 * This class is a JAX-RS Resful Web Service.
 * 
 * @author polinchakb
 *
 */
@Path("user")
@RequestScoped
@WebContext(authMethod="BASIC")
@DeclareRoles({"salesmen"})
@RolesAllowed({"salesmen"})
@SecurityDomain(value="mysqldomain")
public class UserService {
	
	@Inject
	UserEjb userEjb;
	
	@GET
	@Produces("text/xml")
	public List<User> getUsers() throws Exception {
		return this.userEjb.getUsers();
	}
	
	@GET
	@Path("/{id}")
	@Produces("text/xml")
	public User getUser(@PathParam("id") int id) {
		return this.userEjb.getUserById(id);
	}

}
