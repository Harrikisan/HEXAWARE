package com.hexaware.electronicgadgets.entities;

public class Orders {
	private int orderId;
	private Customer customer;
	private String orderDate,status;
	private int TotalAmount;
	
	
	public Orders(int orderId) {
		super();
		this.orderId=orderId;
	}
	//Constructor creation
	public Orders(int orderId, Customer customer, String orderDate, int totalAmount,String status) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.TotalAmount = totalAmount;
		this.status=status;
	}

	

	//Getters and Setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return TotalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalAmount(int totalAmount) {
		TotalAmount = totalAmount;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", TotalAmount="
				+ TotalAmount + "]";
	}
	
	
	
	
}
