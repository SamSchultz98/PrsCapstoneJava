package com.bootcamp.Capstone.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Requestlines")
public class Requestline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonBackReference
	@ManyToOne(optional=false)
	@JoinColumn(name="requestid",columnDefinition="int",insertable=false,updatable=false)
	private Request request;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="productid",columnDefinition="int")
	private Product product;
	
	@Column(nullable = false)
	private int quantity = 1;
	
	
	@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="requestid",columnDefinition="int",insertable=false,updatable=false)
	private Request requestid;
	
	
	
	public Requestline() {}
	
	
	//Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Request getRequestid() {
		return requestid;
	}


	public void setRequestid(Request requestid) {
		this.requestid = requestid;
	}
	
	

}
