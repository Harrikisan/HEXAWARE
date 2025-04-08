package com.hexaware.electronicgadgets.dao.interfaces;

import com.hexaware.electronicgadgets.entities.Products;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;

public interface Productsdao {
	public void addProduct(Products product) throws DatabaseOperationException;
	public void listAllProducts() throws DatabaseOperationException;
	public void updateProductName(String name,int productId) throws DatabaseOperationException;
	public void updateDescription(String description,int productId) throws DatabaseOperationException;
	public void updateprice(int price,int productId) throws DatabaseOperationException;
	public void updateCategory(String category,int productId) throws DatabaseOperationException;
	public void deleteProduct(int productId) throws DatabaseOperationException;
}
