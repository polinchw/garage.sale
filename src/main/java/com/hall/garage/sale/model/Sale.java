package com.hall.garage.sale.model;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
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
	@NamedQuery(name="findAllSales",query="select s from Sale as s")	
})
@XmlRootElement(name="sale")
@XmlAccessorType(XmlAccessType.FIELD)
@Named
@RequestScoped
public class Sale implements Serializable {
	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="user_fk")
	private User user;
	@OneToOne
	@JoinColumn(name="item_fk")
	private Item item;
	@Column(nullable=false)
	private float amount;
	

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
