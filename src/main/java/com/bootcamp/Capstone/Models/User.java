package com.bootcamp.Capstone.Models;

import javax.persistence.*;

@Entity
@Table(name="Users",uniqueConstraints=@UniqueConstraint(name="UIXD_Username",columnNames= {"username"}))
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=30)
	private String username;
	
	@Column(length=30)
	private String password;
	
	@Column(length=30)
	private String firstname;
	
	@Column(length=30)
	private String lastname;
	
	@Column(length=12, nullable=true)
	private String phone;
	
	@Column(length=255, nullable=true)
	private String email;
	
	
	private boolean isreviewer;
	
	
	private boolean isadmin;
	
	public User() {}

	
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isIsreviewer() {
		return isreviewer;
	}

	public void setIsreviewer(boolean isreviewer) {
		this.isreviewer = isreviewer;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	};
	
	
	
	
	
}
