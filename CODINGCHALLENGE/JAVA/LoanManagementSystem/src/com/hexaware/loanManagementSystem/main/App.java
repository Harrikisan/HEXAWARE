package com.hexaware.loanManagementSystem.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.hexaware.loanManagementSystem.daoImpl.UserdaoImpl;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;
import com.hexaware.loanManagementSystem.util.DatabaseConnection;
public class App {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		try {
			String fileName = "Resources/db.properties";

            Connection conn = DatabaseConnection.getConnection(fileName);

            UserdaoImpl user = new UserdaoImpl(conn);
            while(true) {
            	System.out.println("\n Loan Management System");
                System.out.println("------------------------");
                System.out.println("Enter your Choise");
                System.out.println("1.Manage Customer \n"
                		+ "2.Manage Loan \n"
                		+ "3.exit");
                int n=sc.nextInt();
                try {
                    n = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number!");
                }
                switch(n) {
                case 1->user.ManageCustomer();
                case 2->user.ManageLoan();
                case 3->{
                	System.out.println("EXIT");
                	return;
                }
                default -> System.out.println("Enter a valid choice.");
                }
            }
            
            
		}catch (SQLException e) {

        	throw new DatabaseConnectionException("An error occurred: " + e.getMessage());
            

        }
	}
}
