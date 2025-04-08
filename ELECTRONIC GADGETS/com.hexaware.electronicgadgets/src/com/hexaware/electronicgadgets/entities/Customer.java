package com.hexaware.electronicgadgets.entities;

public class Customer {
	private int customerId;
	private String firstName,lastName,email,phone,address;
	
	
	public Customer() {}
	
	public Customer(int customerid) {
		super();
		this.customerId=customerid;
	}
	//Constructor initialization.
	public Customer(int customerId, String firstName, String lastName, String email, String phone, String address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	//Getters and Setters
	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
		
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
		
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
	
}
