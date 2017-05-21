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
 * This class is an example of an EJB Entity Bean.  It uses JPA to 
 * map it's fields to columns in the database.  It's also is a CDI
 * bean that can be injected into other objects.  It's also a JAXB object
 * that can be used to map it's fields into XML or JSON elements.  It uses
 * bean validation to enforce validation rules.  These rules are enforced
 * when the user submits a form on a JSF Facelet page.
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

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="name",unique=true,length=10)
	@NotNull
	@Size(min=1,max=10)
	private String name;   
	
	@Column(name="password",length=100)
	@NotNull
	@Size(min=1,max=100)
	private String password;   
	
	private static final long serialVersionUID = 1L;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
   
}
