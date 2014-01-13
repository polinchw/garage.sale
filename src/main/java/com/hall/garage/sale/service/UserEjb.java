package com.hall.garage.sale.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hall.garage.sale.model.User;

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
	
	public void addUser(User user) {
		em.persist(user);
	}
	
	public void addUser() {
		User user = new User();
		user.setName(this.user.getName());
		addUser(user);
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

}
