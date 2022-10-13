package com.bootcamp.Capstone.Models;


import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Requests")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=80)
	private String description;
	
	@Column(length=80)
	private String justification;
	
	@Column(length=80,nullable=true)
	private String RejectionReason;
	
	@Column(length=20)
	private String deliverymode = "Pickup";
	
	@Column(length=10)
	private String status = "NEW";
	
	@Column(columnDefinition="decimal (11,2) not null")
	private double total = 0;
	
	@ManyToOne
	@JoinColumn(name="userid",columnDefinition="int")
	private User user;
	
	@JsonManagedReference
	@OneToMany
	@Column(name="requestlineid",columnDefinition="int",updatable=false,insertable=false)
	private List<Requestline> requestlines;
	
	public Request() {}


	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getRejectionReason() {
		return RejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		RejectionReason = rejectionReason;
	}

	public String getDeliverymode() {
		return deliverymode;
	}

	public void setDeliverymode(String deliverymode) {
		this.deliverymode = deliverymode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double d) {
		this.total = d;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public List<Requestline> getRequestlines() {
		return requestlines;
	}



	public void setRequestlines(List<Requestline> requestlines) {
		this.requestlines = requestlines;
	}



	



	







	



	



	
	
	
	
	
}

