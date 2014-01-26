package com.hall.garage.sale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Sale
 *
 */
@Entity
@Table(name="sale")
@NamedQueries({
	@NamedQuery(name="findAllSales",query="select s from Sale as s"),
	@NamedQuery(name="findSalesByUser",query="select s from Sale as s where s.user.id = :id")
})
@XmlRootElement(name="sale")
@XmlAccessorType(XmlAccessType.FIELD)
@Named
@RequestScoped
public class Sale implements Serializable {
	
//	@Inject
//	Logger log;
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="user_fk")
	private User user;
	
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="item_fk",nullable=true,unique=false)
	private Item item;
	 
	@Column(nullable=false)
	@NotNull
	@DecimalMin(value="0.01")
	@DecimalMax(value="1000.00")
	private float amount;
	
	@Column(name = "timeOfSale")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeOfSale;

	public Date getTimeOfSale() {
		return timeOfSale;
	}

	public void setTimeOfSale(Date timeOfSale) {
		this.timeOfSale = timeOfSale;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
   
}
