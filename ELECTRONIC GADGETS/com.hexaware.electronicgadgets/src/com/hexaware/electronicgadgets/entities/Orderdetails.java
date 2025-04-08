package com.hexaware.electronicgadgets.entities;

public class Orderdetails {
	private int orderDetailId,quantity;
	private Orders orderId;
	private Products productId;

	//Constructor creation
	public Orderdetails(int orderDetailId, Orders orderId, Products productId, int quantity) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	//Getters and Setters
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Orders getOrderId() {
		return orderId;
	}

	public void setOrderId(Orders orderId) {
		this.orderId = orderId;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//toString
	@Override
	public String toString() {
		return "Orderdetails [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", productId=" + productId
				+ ", quantity=" + quantity + "]";
	}
	
	
	
	
}
