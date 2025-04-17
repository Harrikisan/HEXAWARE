package com.hexaware.loanManagementSystem.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hexaware.loanManagementSystem.dao.Customerdao;
import com.hexaware.loanManagementSystem.entities.Customers;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;

public class CustomerdaoImpl implements Customerdao{

	Connection conn;

	public CustomerdaoImpl(Connection conn) {
		
		this.conn = conn;
	}

	@Override
	public void RegisterCustomer(Customers customer) throws DatabaseConnectionException {
	    String query = "INSERT INTO customer (name, email_address, phone_number, address, credit_score) VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setString(1, customer.getName());
	        ps.setString(2, customer.getEmail());
	        ps.setString(3, customer.getPhone());
	        ps.setString(4, customer.getAddress());
	        ps.setInt(5, customer.getCreditscore());

	        ps.executeUpdate();
	    } catch (SQLException e) {
	        throw new DatabaseConnectionException("Error: " + e.getMessage());
	    }
	}

	
	
	
}
