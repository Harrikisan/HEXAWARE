package com.hexaware.loanManagementSystem.entities;

public class HomeLoan extends Loan{

	private String propertyName;
    private double propertyValue;
	
    
    


	public HomeLoan(int loanTerm, Customers customerId, double principleAmount, double intrestRate, String loanType,
			String loanStatus, String propertyName, double propertyValue) {
		super(loanTerm, customerId, principleAmount, intrestRate, loanType, loanStatus);
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}


	public String getPropertyName() {
		return propertyName;
	}


	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	public double getPropertyValue() {
		return propertyValue;
	}


	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}
    
    
    
}
