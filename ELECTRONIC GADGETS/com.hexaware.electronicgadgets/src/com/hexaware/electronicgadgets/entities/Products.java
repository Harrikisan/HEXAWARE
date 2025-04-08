package com.hexaware.electronicgadgets.entities;

public class Products {
	private int productId;
	private String productName,description,category;
	

	private int price;
	
	public Products(int productId) {
		super();
		this.productId=productId;
	}
	
	
	//constructor creation
	
	public Products(int productId, String productName, String description, int price,String category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category=category;
	}

	
	//Getters and setters
	
	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	
	//toString
	
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ ", price=" + price + "]";
	}
	
	
	
	
	
}
