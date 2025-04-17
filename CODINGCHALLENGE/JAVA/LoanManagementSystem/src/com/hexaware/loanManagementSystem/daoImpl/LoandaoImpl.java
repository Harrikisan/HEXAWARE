package com.hexaware.loanManagementSystem.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.loanManagementSystem.dao.Loandao;
import com.hexaware.loanManagementSystem.entities.CarLoan;
import com.hexaware.loanManagementSystem.entities.HomeLoan;
import com.hexaware.loanManagementSystem.entities.Loan;
import com.hexaware.loanManagementSystem.exceptions.DatabaseConnectionException;
import com.hexaware.loanManagementSystem.exceptions.InvalidLoanException;
import com.hexaware.loanManagementSystem.exceptions.LoanIdNotFoundException;

public class LoandaoImpl implements Loandao{

	Connection conn;
	

	public LoandaoImpl(Connection conn) {
		
		this.conn = conn;
	}

	public void ApplyLoan(Loan loan) throws DatabaseConnectionException {
	    String query = "INSERT INTO loan (customer_id, principle_amount, interest_rate, loan_term, loan_type, loan_status) " +
	                   "VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        ps.setInt(1, loan.getCustomerId().getCustomerId());
	        ps.setDouble(2, loan.getPrincipleAmount());
	        ps.setDouble(3, loan.getIntrestRate());
	        ps.setInt(4, loan.getLoanTerm());
	        ps.setString(5, loan.getLoanType());
	        ps.setString(6, loan.getLoanStatus());

	        ps.executeUpdate();

	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) {
	                int generatedLoanId = rs.getInt(1);
	                loan.setLoanId(generatedLoanId);
	            }
	        }

	    } catch (SQLException e) {
	        throw new DatabaseConnectionException("Error inserting into loan table: " + e.getMessage());
	    }

	    if (loan instanceof HomeLoan) {
	        String query1 = "INSERT INTO homeloan (loan_id, property_name, property_value) VALUES (?, ?, ?)";
	        HomeLoan homeLoan = (HomeLoan) loan;

	        try (PreparedStatement ps1 = conn.prepareStatement(query1)) {
	            ps1.setInt(1, loan.getLoanId());
	            ps1.setString(2, homeLoan.getPropertyName());
	            ps1.setDouble(3, homeLoan.getPropertyValue());

	            ps1.executeUpdate();
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error inserting into homeloan table: " + e.getMessage());
	        }
	    }

	    if (loan instanceof CarLoan) {
	        String query2 = "INSERT INTO carloan (loan_id, car_name, car_value) VALUES (?, ?, ?)";
	        CarLoan carLoan = (CarLoan) loan;

	        try (PreparedStatement ps2 = conn.prepareStatement(query2)) {
	            ps2.setInt(1, loan.getLoanId());
	            ps2.setString(2, carLoan.getCarName());
	            ps2.setDouble(3, carLoan.getCarValue());

	            ps2.executeUpdate();
	        } catch (SQLException e) {
	            throw new DatabaseConnectionException("Error inserting into carloan table: " + e.getMessage());
	        }
	    }
	}


	@Override
	public void CalculateIntrest(int loanId) throws InvalidLoanException, DatabaseConnectionException {
	    String query = "SELECT principle_amount, interest_rate, loan_term FROM loan WHERE loan_id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, loanId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                double principleAmount = rs.getDouble("principle_amount");
	                double interestRate = rs.getDouble("interest_rate");
	                int loanTerm = rs.getInt("loan_term");

	                double interest = CalculateIntrest(principleAmount, interestRate, loanTerm);
	                System.out.println("Interest Amount for Loan ID " + loanId + " is: " + interest);
	            } else {
	                throw new InvalidLoanException("Loan with ID " + loanId + " not found.");
	            }
	        }
	    } catch (SQLException e) {
	        throw new DatabaseConnectionException("Error: " + e.getMessage());
	    }
	}

	// Overloaded method
	public double CalculateIntrest(double principleAmount, double interestRate, int loanTerm) {
	    return (principleAmount * interestRate * loanTerm) / 12;
	}


	@Override
	public void LoanStatus(int loanId) throws LoanIdNotFoundException {
	    String query = "SELECT loan_status FROM loan WHERE loan_id = ?";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, loanId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                String status = rs.getString("loan_status");
	                System.out.println("Loan ID: " + loanId + " Status: " + status);
	            } else {
	                System.out.println("No loan found with Loan ID: " + loanId);
	            }
	        }
	    } catch (SQLException e) {
	        throw new LoanIdNotFoundException("Error: " + e.getMessage());
	    }
	}


	@Override
	public void CalculateEMI(int loanId) throws DatabaseConnectionException, InvalidLoanException {
	    String query = "SELECT principle_amount, interest_rate, loan_term FROM loan WHERE loan_id = ?";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, loanId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                double pAmount = rs.getDouble("principle_amount");
	                double annualRate = rs.getDouble("interest_rate");
	                int loanTerm = rs.getInt("loan_term");

	                double emi = CalculateEMI(pAmount, annualRate, loanTerm);
	                System.out.printf("EMI for Loan ID: "+loanId+" is "+ emi);
	            } else {
	                throw new InvalidLoanException("Loan not found with ID: " + loanId);
	            }
	        }
	    } catch (SQLException e) {
	        throw new DatabaseConnectionException("Error: " + e.getMessage());
	    }
	}

	public double CalculateEMI(double pAmount, double annualRate, int loanTerm) {
	    double monthlyRate = annualRate / (12 * 100);
	    int totalMonths = loanTerm;

	    double emi = (pAmount * monthlyRate * Math.pow(1 + monthlyRate, totalMonths)) /
	                 (Math.pow(1 + monthlyRate, totalMonths) - 1);
	    return emi;
	}


	@Override
	public void LoanRepayment(int loanId, double amount) throws InvalidLoanException {
	    String query = "SELECT * FROM loan WHERE loan_id = ?";
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, loanId);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                double pAmount = rs.getDouble("principle_amount");
	                double annualRate = rs.getDouble("interest_rate");
	                int loanTerm = rs.getInt("loan_term");
	                String status = rs.getString("loan_status");

	                double emi = CalculateEMI(pAmount, annualRate, loanTerm);

	                if (amount < emi) {
	                    System.out.println("Payment is less than the EMI amount. Payment rejected.");
	                    return;
	                }

	                int numberOfEmis = (int) (amount / emi);

	                double newPrincipalAmount = pAmount - (numberOfEmis * emi);

	                if (newPrincipalAmount <= 0) {
	                    status = "Paid Off";
	                    newPrincipalAmount = 0;
	                }

	                String updateQuery = "UPDATE loan SET principle_amount = ?, loan_status = ? WHERE loan_id = ?";
	                try (PreparedStatement psUpdate = conn.prepareStatement(updateQuery)) {
	                    psUpdate.setDouble(1, newPrincipalAmount);
	                    psUpdate.setString(2, status);
	                    psUpdate.setInt(3, loanId);

	                    psUpdate.executeUpdate();
	                    System.out.println("Successfully paid " + numberOfEmis + " EMIs. Remaining loan balance: " + newPrincipalAmount);
	                }
	            } else {
	                System.out.println("Loan with the specified ID not found.");
	            }
	        }
	    } catch (SQLException e) {
	        throw new InvalidLoanException("Error: " + e.getMessage());
	    }
	}


	@Override
	public void getAllLoans() throws InvalidLoanException {
	    
		String query = "SELECT * FROM loan";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query); 
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            int loanId = rs.getInt("loan_id");
	            int customerId = rs.getInt("customer_id");
	            double principleAmount = rs.getDouble("principle_amount");
	            double interestRate = rs.getDouble("interest_rate");
	            int loanTerm = rs.getInt("loan_term");
	            String loanType = rs.getString("loan_type");
	            String loanStatus = rs.getString("loan_status");
	            
	            System.out.println("Loan ID: " + loanId + ", Customer ID: " + customerId + 
	                               ", Principle Amount: " + principleAmount + ", Interest Rate: " + 
	                               interestRate + ", Loan Term: " + loanTerm + ", Loan Type: " + 
	                               loanType + ", Loan Status: " + loanStatus);

	            if (loanType.equals("Homeloan")) {
	                String homeLoanQuery = "SELECT * FROM homeloan WHERE loan_id = ?";
	                try (PreparedStatement ps1 = conn.prepareStatement(homeLoanQuery)) {
	                    ps1.setInt(1, loanId);
	                    try (ResultSet rs1 = ps1.executeQuery()) {
	                        if (rs1.next()) {
	                            String propertyName = rs1.getString("property_name");
	                            double propertyValue = rs1.getDouble("property_value");
	                            System.out.println("Property Name: " + propertyName + ", Property Value: " + propertyValue);
	                        }
	                    }
	                }
	            } else if (loanType.equals("Carloan")) {
	                String carLoanQuery = "SELECT * FROM carloan WHERE loan_id = ?";
	                try (PreparedStatement ps2 = conn.prepareStatement(carLoanQuery)) {
	                    ps2.setInt(1, loanId);
	                    try (ResultSet rs2 = ps2.executeQuery()) {
	                        if (rs2.next()) {
	                            String carName = rs2.getString("car_name");
	                            double carValue = rs2.getDouble("car_value");
	                            System.out.println("Car Name: " + carName + ", Car Value: " + carValue);
	                        }
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new InvalidLoanException("Error retrieving loans: " + e.getMessage());
	    }
	}

	@Override
	public void getLoanById(int loanId) throws LoanIdNotFoundException {
	    String query = "SELECT * FROM loan WHERE loan_id = ?";
	    
	    try (PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, loanId);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int customerId = rs.getInt("customer_id");
	                double principleAmount = rs.getDouble("principle_amount");
	                double interestRate = rs.getDouble("interest_rate");
	                int loanTerm = rs.getInt("loan_term");
	                String loanType = rs.getString("loan_type");
	                String loanStatus = rs.getString("loan_status");
	                
	                System.out.println("Loan ID: " + loanId + ", Customer ID: " + customerId + 
	                                   ", Principle Amount: " + principleAmount + ", Interest Rate: " + 
	                                   interestRate + ", Loan Term: " + loanTerm + ", Loan Type: " + 
	                                   loanType + ", Loan Status: " + loanStatus);

	                if (loanType.equals("Homeloan")) {
	                    String homeLoanQuery = "SELECT * FROM homeloan WHERE loan_id = ?";
	                    try (PreparedStatement ps1 = conn.prepareStatement(homeLoanQuery)) {
	                        ps1.setInt(1, loanId);
	                        try (ResultSet rs1 = ps1.executeQuery()) {
	                            if (rs1.next()) {
	                                String propertyName = rs1.getString("property_name");
	                                double propertyValue = rs1.getDouble("property_value");
	                                System.out.println("Property Name: " + propertyName + ", Property Value: " + propertyValue);
	                            }
	                        }
	                    }
	                } else if (loanType.equals("Carloan")) {
	                    String carLoanQuery = "SELECT * FROM carloan WHERE loan_id = ?";
	                    try (PreparedStatement ps2 = conn.prepareStatement(carLoanQuery)) {
	                        ps2.setInt(1, loanId);
	                        try (ResultSet rs2 = ps2.executeQuery()) {
	                            if (rs2.next()) {
	                                String carName = rs2.getString("car_name");
	                                double carValue = rs2.getDouble("car_value");
	                                System.out.println("Car Name: " + carName + ", Car Value: " + carValue);
	                            }
	                        }
	                    }
	                }
	            } else {
	                System.out.println("No loan found with the given Loan ID.");
	            }
	        }
	    } catch (SQLException e) {
	        throw new LoanIdNotFoundException("Error: " + e.getMessage());
	    }
	}






}
