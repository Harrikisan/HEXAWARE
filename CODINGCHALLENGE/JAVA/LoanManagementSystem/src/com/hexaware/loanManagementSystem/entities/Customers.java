package com.hexaware.loanManagementSystem.entities;

public class Customers {

	private int customerId,creditscore;
	private String name,email,address,phone;
	
	
	public Customers(int customerId) {
		this.customerId=customerId;
	}
	public Customers(String phone, String name, String email, String address,int score) {
		super();
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.address = address;
		this.creditscore=score;
	}


	public int getCreditscore() {
		return creditscore;
	}
	public void setCreditscore(int creditscore) {
		this.creditscore = creditscore;
	}
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
