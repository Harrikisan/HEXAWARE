package com.hexaware.loanManagementSystem.entities;

public class Loan {

	private int loanId,loanTerm;
	private Customers customerId;
	private double principleAmount,intrestRate;
	private String loanType,loanStatus;
	public Loan( int loanTerm, Customers customerId, double principleAmount, double intrestRate,
			String loanType, String loanStatus) {
		super();
		this.loanTerm = loanTerm;
		this.customerId = customerId;
		this.principleAmount = principleAmount;
		this.intrestRate = intrestRate;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}
	public Loan( int loanId,int loanTerm, Customers customerId, double principleAmount, double intrestRate,
			String loanType, String loanStatus) {
		super();
		this.loanId=loanId;
		this.loanTerm = loanTerm;
		this.customerId = customerId;
		this.principleAmount = principleAmount;
		this.intrestRate = intrestRate;
		this.loanType = loanType;
		this.loanStatus = loanStatus;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	public Customers getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}
	public double getPrincipleAmount() {
		return principleAmount;
	}
	public void setPrincipleAmount(double principleAmount) {
		this.principleAmount = principleAmount;
	}
	public double getIntrestRate() {
		return intrestRate;
	}
	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	
	
}
