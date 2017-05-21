package com.hall.garage.sale.ws.soap;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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
	@EJB
	UserEjb userEjb;
	
	@WebMethod
	public String addUser(@WebParam(name="name") String name, @WebParam(name = "password") String password) throws Exception {
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		userEjb.addUser(user);
		return user.getId().toString();
	}
	

}
