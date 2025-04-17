package com.hexaware.loanManagementSystem.entities;

public class CarLoan extends Loan{

	private String carName;
    private double carValue;
	
	public CarLoan(int loanTerm, Customers customerId, double principleAmount, double intrestRate, String loanType,
			String loanStatus, String carName, double carValue) {
		super(loanTerm, customerId, principleAmount, intrestRate, loanType, loanStatus);
		this.carName = carName;
		this.carValue = carValue;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public double getCarValue() {
		return carValue;
	}
	public void setCarValue(double carValue) {
		this.carValue = carValue;
	}
    
    
}
