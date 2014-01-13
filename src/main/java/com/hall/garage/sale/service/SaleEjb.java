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

import com.hall.garage.sale.model.Sale;
import com.hall.garage.sale.model.User;

/**
 * Session Bean implementation class SaleService
 */
@Stateless
@LocalBean
@Named
public class SaleEjb {
	
	private final static Logger logger = Logger.getLogger(SaleEjb.class.getName());
	
	@PersistenceContext(unitName="garage.sale")
	private EntityManager em;
	
	@Inject
	Sale sale;
	
	@Inject
	UserEjb userEjb;
	
	String selectedUser;
	
	public String getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}

	public void makeSale() {
		logger.fine("selected User: "+this.selectedUser);
		Sale sale = new Sale();
		User user = this.userEjb.getUserById(Integer.parseInt(this.selectedUser));
		sale.setUser(user);
		sale.setAmount(this.sale.getAmount());
//		Item item = new Item();
//		sale.setItem(item);
//		user.setName(this.sale.getUser().getName());
//		item.setName(this.sale.getItem().getName());
		em.persist(sale);
	}
	
	public List<Sale> getSales() {
	    Query q = em.createNamedQuery("findAllSales");
	    return q.getResultList();
	}


}
