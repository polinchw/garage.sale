package com.hall.garage.sale.ws.soap;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.hall.garage.sale.model.User;
import com.hall.garage.sale.service.UserEjb;

/**
 * This class is a JAX-WS Soap Web Service.  
 * 
 * @author polinchakb
 *
 */
@WebService
public class Garage {
	
	/**
	 * An EJB that is injected by the container.
	 */
	@Inject
	UserEjb userEjb;
	
	@WebMethod
	public void addUser(@WebParam(name="name") String name) throws Exception {
		User user = new User();
		user.setName(name);
		userEjb.addUser(user);
	}
	

}
