package com.hexaware.loanManagementSystem.dao;

import com.hexaware.loanManagementSystem.entities.Customers;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;

public interface Customerdao {

	public void RegisterCustomer(Customers customer) throws DatabaseConnectionException;
}
