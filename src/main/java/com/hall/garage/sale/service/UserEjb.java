package com.hall.garage.sale.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.SecurityDomain;


import com.hall.garage.sale.model.Roles;
import com.hall.garage.sale.model.User;


/**
 * Stateless EJB Bean implementation class UserEjb
 */
@Stateless
@LocalBean
@Named
//@SecurityDomain(value="mysqldomain")
//@DeclareRoles({"salesmen"})
//@RolesAllowed({"salesmen"})
public class UserEjb {
	
	private Logger logger = Logger.getLogger(UserEjb.class.getName());
	
	@PersistenceContext(unitName="garage.sale")
	private EntityManager em;
	
	@Inject 
	User user;
	
	@Inject
	RolesEjb rolesEjb;
	
	public void addUser(User user) {
		em.persist(user);
	}
	
	
	public void addUser() {
		User user = new User();
		user.setName(this.user.getName());
		user.setPassword(UserEjb.hashPassword(this.user.getPassword()));
		addUser(user);
		Roles roles = new Roles();
		roles.setName(this.user.getName());
		roles.setRole("salesmen");
		this.rolesEjb.addRoles(roles);
	}
	
	@SuppressWarnings("unchecked")
	@RolesAllowed({"salesmen"})
	public List<User> getUsers() {
		Query q = em.createNamedQuery("findAllUsers");
		List<User> list = q.getResultList();
		logger.fine("User count: "+list.size());
		return list;
	}
	
	public User getUserById(int id) {
		User u = em.find(User.class, id);
		return u;
	}
	
	private static String hashPassword(String password) {
		return org.jboss.crypto.CryptoUtil.createPasswordHash("MD5", "BASE64", null, null, password);
	}

}
