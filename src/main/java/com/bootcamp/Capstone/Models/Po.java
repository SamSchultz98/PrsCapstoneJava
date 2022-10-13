package com.bootcamp.Capstone.Models;

public class Po {

	private Vendor Vendor;
	private Iterable<Poline> Polines;
	private double PoTotal;
	
	
	public Po() {}
	
	
	
	
	
	
	
	public Vendor getVendor() {
		return Vendor;
	}
	public void setVendor(Vendor vendor) {
		Vendor = vendor;
	}
	public Iterable<Poline> getPolines() {
		return Polines;
	}
	public void setPolines(Iterable<Poline> polines) {
		Polines = polines;
	}
	public double getPoTotal() {
		return PoTotal;
	}
	public void setPoTotal(double poTotal) {
		PoTotal = poTotal;
	}
	
}
