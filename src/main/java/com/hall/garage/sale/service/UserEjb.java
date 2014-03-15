package com.hall.garage.sale.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hall.garage.sale.model.Roles;
import com.hall.garage.sale.model.User;

import org.jboss.security.Base64Utils;

/**
 * Stateless EJB Bean implementation class UserEjb
 */
@Stateless
@LocalBean
@Named
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
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
		byte[] hash = md.digest(password.getBytes());
		String passwordHash = Base64Utils.tob64(hash);
		return passwordHash;
	}

}
