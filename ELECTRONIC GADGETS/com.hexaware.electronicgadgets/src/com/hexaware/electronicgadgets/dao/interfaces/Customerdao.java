package com.hexaware.electronicgadgets.dao.interfaces;

import com.hexaware.electronicgadgets.entities.Customer;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.exceptions.InputValidationException;

public interface Customerdao {
	public void addCustomer(Customer customer) throws DatabaseOperationException, InputValidationException;
	public void listAllCustomers() throws DatabaseOperationException;
	public void updateFirstName(String fName,int id) throws DatabaseOperationException, InputValidationException;
	public void updateLastName(String lName,int id) throws DatabaseOperationException, InputValidationException;
	public void updateEmail(String email,int id) throws DatabaseOperationException, InputValidationException;
	public void updatePhoneNumber(String phone,int id) throws DatabaseOperationException, InputValidationException;
	public void updateAddress(String address,int id)throws DatabaseOperationException, InputValidationException;
	public void deleteCustomer(int id) throws DatabaseOperationException;
	public void CalculateTotalOrders(int id) throws DatabaseOperationException;
}
