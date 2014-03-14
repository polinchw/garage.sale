package com.hall.garage.sale.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hall.garage.sale.model.Roles;

@Stateless
@LocalBean
@Named
public class RolesEjb {
	
	@PersistenceContext(unitName="garage.sale")
	private EntityManager em;
	
	public void addRoles(Roles roles) {
		em.persist(roles);
	}
}
