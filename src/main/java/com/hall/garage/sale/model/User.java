package com.hall.garage.sale.model;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="findUsersByName",query="select u from User as u where u.name = :name"),
	@NamedQuery(name="findAllUsers",query="select u from User as u")	
})
@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@Named
@RequestScoped
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(name="name",unique=true,length=10)
	@NotNull
	@Size(min=1,max=10)
	private String name;   
	
	private static final long serialVersionUID = 1L;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
   
}
