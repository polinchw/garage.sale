package com.hall.garage.sale.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hall.garage.sale.model.Item;
import com.hall.garage.sale.model.Sale;
import com.hall.garage.sale.model.User;

/**
 * This is a Stateless EJB Session Bean.  It's used to control database 
 * transactions.  When one of it's public methods are called, by default, if 
 * a database transaction is not already underway in the thread it will start
 * one.  
 * 
 * It also shows how CDI beans are injected such as the sale and item properties.
 */
@Stateless
@LocalBean
@Named
public class SaleEjb {
	
	private final static Logger logger = Logger.getLogger(SaleEjb.class.getName());
	
	
    @PersistenceContext(unitName="garage.sale")
	private EntityManager em;
	
	/**
	 * A CDI bean that is injected by the JavaEE container.  This sale object
	 * is used by a Facelet page.  It has a request scope. 
	 */
	@Inject
	Sale sale;
	
	@Inject
	Item item;
	
	@Inject
	UserEjb userEjb;
	
	Date date = new Date();
	
	String selectedUser;
	
	UIComponent addedComponent;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UIComponent getAddedComponent() {
		return addedComponent;
	}

	public void setAddedComponent(UIComponent addedComponent) {
		this.addedComponent = addedComponent;
	}

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
		if(this.item.getName() != null ||
		   this.item.getDescription() != null) {
			Item item = new Item();
			//add the item to the sale
			sale.setItem(item);		
			item.setName(this.item.getName());
			item.setDescription(this.item.getDescription());
		}
		//add everything to the database
		em.persist(sale);
		FacesContext.getCurrentInstance().addMessage(this.getAddedComponent().getClientId(), new FacesMessage("Sale "+sale.getId()+" added."));
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
	
	public List<String> getItems() {
		List<String> items = new ArrayList<String>();
		items.add("Java is");
		items.add("awesome");
		return items;
	}


}
