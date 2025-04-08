package com.hexaware.electronicgadgets.dao.interfaces;

import com.hexaware.electronicgadgets.entities.Orders;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;

public interface Ordersdao {
	public void addOrder(Orders order)throws DatabaseOperationException;
	public void viewAllOrders()throws DatabaseOperationException;
	public void updateCustomerId(int customerId,int orderId)throws DatabaseOperationException;
	public void updateOrderDate(String date,int orderId)throws DatabaseOperationException;
	public void updateTotalAmount(int amount,int orderId)throws DatabaseOperationException;
	public void updateStatus(String status,int orderId) throws DatabaseOperationException;
	public void cancelOrder(int orderId) throws DatabaseOperationException;
	void calculateTotalAmount(int orderId) throws DatabaseOperationException;
}
