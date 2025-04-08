package com.hexaware.electronicgadgets.entities;

public class Inventory {
	private int inventoryId,quantityInStock;
	private Products productId;
	private String lastStockUpdate;
	
	//Constructor creation
	public Inventory(int inventoryId, Products productId, int quantityInStock, String lastStockUpdate) {
		super();
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantityInStock = quantityInStock;
		this.lastStockUpdate = lastStockUpdate;
	}

	
	// Getters and Setters
	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public String getLastStockUpdate() {
		return lastStockUpdate;
	}

	public void setLastStockUpdate(String lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	
	//toString
	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", productId=" + productId + ", quantityInStock="
				+ quantityInStock + ", lastStockUpdate=" + lastStockUpdate + "]";
	}
	
	
	
	
	
}
