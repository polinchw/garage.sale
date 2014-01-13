package com.hall.garage.sale.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hall.garage.sale.model.Item;
import com.hall.garage.sale.model.Sale;
import com.hall.garage.sale.model.User;

/**
 * Stateless Session EJB implementation class SaleService
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
	Item item;
	
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
		sale.setTimeOfSale(new Date());
		Item item = new Item();
		sale.setItem(item);		
		item.setName(this.item.getName());
		item.setDescription(this.item.getDescription());
		em.persist(sale);
	}
	
	public float getTotalSales() {
		Query q = em.createNamedQuery("findAllSales");
		@SuppressWarnings("unchecked")
		List<Sale> sales = q.getResultList();
		float total = 0.0f;
		for(Sale s : sales) {
			total = s.getAmount() + total;
		}
		return total;
	}
	
	
    /**
     * Total up the sales per user.
     * @return
     */
	public List<Sale> getTotalSalesByUser() {
		List<Sale> totals = new ArrayList<Sale>();
		List<User> users = userEjb.getUsers(); 
		for(User u : users) {
			logger.fine("User: "+u.getName());			
			Query q = em.createNamedQuery("findSalesByUser");
			q.setParameter("id", u.getId());
		    List<Sale> sales = q.getResultList();
		    float total = 0.0f;
		    for(Sale s : sales) {
		    	 //add up all this guys sales
		    	 logger.fine("user: "+s.getUser().getName());
		    	 logger.fine("amount: "+s.getAmount());
		         total = s.getAmount() + total;
		    }		    
		    Sale totalSale = new Sale();		   
		    totalSale.setUser(u);
		    totalSale.setAmount(total);
		    totals.add(totalSale);
		}			
		return totals;
	}
	
	public List<Sale> getSales() {
	    Query q = em.createNamedQuery("findAllSales");
	    return q.getResultList();
	}


}
