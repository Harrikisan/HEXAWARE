package com.hexaware.loanManagementSystem.dao;

import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;
import com.hexaware.loanManagementSystem.exceptions.InvalidLoanException;
import com.hexaware.loanManagementSystem.exceptions.LoanIdNotFoundException;

public interface Userdao {

	public void ManageCustomer() throws DatabaseConnectionException;
	public void ManageLoan() throws DatabaseConnectionException, 
	LoanIdNotFoundException, InvalidLoanException;
}
