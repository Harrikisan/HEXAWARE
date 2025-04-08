package com.hexaware.electronicgadgets.dao.interfaces;

import com.hexaware.electronicgadgets.entities.Inventory;

public interface Inventorydao {
	public void AddToInventory(Inventory inventory);
	public void viewAllProducts();
	public void updateProductId(int productId,int inventoryId);
	public void updateQuantityStock(int stock,int inventoryId);
	public void updatelastStockUpdate(String date,int inventoryId);
	public void deleteFromInventory(int inventoryId);
}
