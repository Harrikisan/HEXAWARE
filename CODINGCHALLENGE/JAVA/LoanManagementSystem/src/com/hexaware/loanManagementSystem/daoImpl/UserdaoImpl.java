package com.hexaware.loanManagementSystem.daoImpl;

import java.sql.Connection;
import java.util.Scanner;

import com.hexaware.loanManagementSystem.dao.Userdao;
import com.hexaware.loanManagementSystem.entities.CarLoan;
import com.hexaware.loanManagementSystem.entities.Customers;
import com.hexaware.loanManagementSystem.entities.HomeLoan;
import com.hexaware.loanManagementSystem.entities.Loan;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;
import com.hexaware.loanManagementSystem.exceptions.InvalidLoanException;
import com.hexaware.loanManagementSystem.exceptions.LoanIdNotFoundException;

public class UserdaoImpl implements Userdao{

	private CustomerdaoImpl customer;
	private LoandaoImpl loan;

	Scanner sc = new Scanner(System.in);

	public UserdaoImpl(Connection conn) {
		this.customer = new CustomerdaoImpl(conn);
		this.loan = new LoandaoImpl(conn);  // ✅ Correct initialization
	}
	@Override
	public void ManageCustomer() throws DatabaseConnectionException {
		// TODO Auto-generated method stub
		System.out.println("1.Reginster Customer");
		System.out.println("Enter Name");
		String name=sc.next();
		System.out.println("Enter email address");
		String email=sc.next();
		System.out.println("Enter phoneNumber");
		String phone=sc.next();
		System.out.println("Enter Address");
		String address=sc.next();
		System.out.println("Enter credit score");
		int score=sc.nextInt();
		
		Customers cust=new Customers(phone, name, email, address,score);
		customer.RegisterCustomer(cust);
	}

	@Override
	public void ManageLoan() throws DatabaseConnectionException, 
	LoanIdNotFoundException, InvalidLoanException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter your choise:");
		System.out.println(""
				+ "1.ApplyLoan \n"
				+ "2.Calculate Intrest \n"
				+ "3.loanStatus \n"
				+ "4.Calculate EMI \n"
				+ "5.Loan repayment \n"
				+ "6.get all loans \n"
				+ "7.get loan by id");
		int n=sc.nextInt();
		
		
		switch(n) {
		case 1:
			System.out.println("Enter customerid");
			int customerId=sc.nextInt();
			System.out.println("Enter Principle amount");
			double pAmount=sc.nextDouble();
			System.out.println("Enter intrest rate");
			double intrestRate=sc.nextDouble();
			System.out.println("Enter loan term");
			int loanTerm=sc.nextInt();
			System.out.println("Enter loan type");
			String loanType=sc.next();
			System.out.println("Enter loan status");
			String status=sc.next();
			String propertyName;
			Double propertyValue;
			Customers cust=new Customers(customerId);
			 if (loanType.equalsIgnoreCase("Homeloan")) {
	                System.out.println("Enter property name:");
	                propertyName = sc.next();
	                System.out.println("Enter property value:");
	                propertyValue = sc.nextDouble();
	                
	                HomeLoan homeLoan = new HomeLoan(loanTerm, cust, pAmount, intrestRate, loanType, status, propertyName, propertyValue);
	                loan.ApplyLoan(homeLoan);
	            } else if (loanType.equalsIgnoreCase("Carloan")) {
	                System.out.println("Enter car name:");
	                propertyName = sc.next();
	                System.out.println("Enter car value:");
	                propertyValue = sc.nextDouble();
	                
	                CarLoan carLoan = new CarLoan(loanTerm, cust, pAmount, intrestRate, loanType, status, propertyName, propertyValue);
	                loan.ApplyLoan(carLoan);
	            } else {
	                System.out.println("Invalid loan type entered.");
	            }
			 break;
		case 2:
			System.out.println("Enter ID");
			int id=sc.nextInt();
			loan.CalculateIntrest(id);
			break;
		case 3:
			System.out.println("Enter id");
			id=sc.nextInt();
			
			loan.LoanStatus(id);
			break;
		case 4:
			System.out.println("Enter id");
			id=sc.nextInt();
			loan.CalculateEMI(id);
			break;
		case 5:
			System.out.println("Enter id");
			id=sc.nextInt();
			System.out.println("Enter amount");
			pAmount=sc.nextDouble();
			loan.LoanRepayment(id, pAmount);
			break;
		case 6:
			loan.getAllLoans();
			break;
		case 7:
			System.out.println("Enter id");
			id=sc.nextInt();
			loan.getLoanById(id);
			break;
			
		}
		
	}

}
