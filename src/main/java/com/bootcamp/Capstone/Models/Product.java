package com.bootcamp.Capstone.Models;

import javax.persistence.*;

@Entity
@Table(name="Products",uniqueConstraints=@UniqueConstraint(name="UIXD_Partnbr",columnNames= {"partnbr"}))
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=30)
	private String partnbr;
	
	@Column(length=30)
	private String name;
	
	@Column(columnDefinition="decimal (11,2) not null")
	private double price;
	
	@Column(length=30)
	private String unit;
	
	@Column(length=255,nullable=true)
	private String photopath;
	
	//FK to Vendor
	@ManyToOne(optional=false)
	@JoinColumn(name="vendorid", columnDefinition="int")
	private Vendor vendor;

	public Product() {}
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartnbr() {
		return partnbr;
	}

	public void setPartnbr(String partnbr) {
		this.partnbr = partnbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	
	

}
