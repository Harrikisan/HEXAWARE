package com.hexaware.loanManagementSystem.dao;

import com.hexaware.loanManagementSystem.entities.Loan;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;
import com.hexaware.loanManagementSystem.exceptions.InvalidLoanException;
import com.hexaware.loanManagementSystem.exceptions.LoanIdNotFoundException;

public interface Loandao {

	public void ApplyLoan(Loan loan) throws DatabaseConnectionException;
	public void CalculateIntrest(int loanId) throws InvalidLoanException, DatabaseConnectionException;
	public void LoanStatus(int loanId) throws LoanIdNotFoundException;
	public void CalculateEMI(int loanId) throws DatabaseConnectionException, InvalidLoanException;
	public void LoanRepayment(int loanId,double amount) throws InvalidLoanException;
	public void getAllLoans() throws DatabaseConnectionException, InvalidLoanException;
	public void getLoanById(int loanId) throws  InvalidLoanException, LoanIdNotFoundException;
}
