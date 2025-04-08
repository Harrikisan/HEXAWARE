package com.hexaware.electronicgadgets.dao.interfaces;

import com.hexaware.electronicgadgets.entities.Orderdetails;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;

public interface Orderdetailsdao {
	public void addOrderInfo(Orderdetails orderdetail)throws DatabaseOperationException;
	public void viewAllInfo()throws DatabaseOperationException;
	public void updateOrderId(int orderId,int infoId)throws DatabaseOperationException;
	public void upadteProductId(int productId,int infoId)throws DatabaseOperationException;
	public void updateQuantity(int quantity,int infoId)throws DatabaseOperationException;
	public void calculateSubTotal(Orderdetails orderdetail)throws DatabaseOperationException;
	public void addDiscount(int amount,int infoid)throws DatabaseOperationException;
	public void deleteInfo(int infoId) throws DatabaseOperationException;
}
