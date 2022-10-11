package com.bootcamp.Capstone.Models;

import javax.persistence.*;

@Entity
@Table(name="Requestlines")
public class Requestline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="requestid",columnDefinition="int")
	private Request request;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="productid",columnDefinition="int")
	private Product product;
	
	@Column(nullable = false)
	private int quantity = 1;
	
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
	
	

}
