package com.hexaware.electronicgadgets.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.hexaware.electronicgadgets.entities.Customer;
import com.hexaware.electronicgadgets.dao.interfaces.Customerdao;
import com.hexaware.electronicgadgets.util.DatabaseConnection;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.exceptions.InputValidationException;

public class CustomerdaoImpl implements Customerdao {

    private Connection conn = DatabaseConnection.getConnection();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public void addCustomer(Customer customer) throws DatabaseOperationException, InputValidationException {
        String query = "INSERT INTO customers(customer_id, first_name, last_name, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";

        validateCustomer(customer);

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Customer ID already exists.", e);
        }
    }

    @Override
    public void listAllCustomers() throws DatabaseOperationException {
        String query = "SELECT * FROM customers";
        ArrayList<Customer> customers = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address")
                ));
            }

            for (Customer c : customers) {
                System.out.println(String.format(
                        "ID: %d, FirstName: %s, LastName: %s, Email: %s, Phone: %s, Address: %s",
                        c.getCustomerId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone(), c.getAddress()));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to list customers.", e);
        }
    }

    @Override
    public void deleteCustomer(int id) throws DatabaseOperationException {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete customer, ID does not exist.", e);
        }
    }

    @Override
    public void updateFirstName(String fName, int id) throws DatabaseOperationException, InputValidationException {
        if (fName == null || fName.trim().isEmpty()) {
            throw new InputValidationException("First name cannot be empty.");
        }
        String query = "UPDATE customers SET first_name = ? WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, fName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update first name.", e);
        }
    }

    @Override
    public void updateLastName(String lName, int id) throws DatabaseOperationException, InputValidationException {
        if (lName == null || lName.trim().isEmpty()) {
            throw new InputValidationException("Last name cannot be empty.");
        }
        String query = "UPDATE customers SET last_name = ? WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, lName);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update last name.", e);
        }
    }

    @Override
    public void updateEmail(String email, int id) throws DatabaseOperationException, InputValidationException {
        if (!isValidEmail(email)) {
            throw new InputValidationException("Enter a valid email.");
        }
        String query = "UPDATE customers SET email = ? WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update email.", e);
        }
    }

    @Override
    public void updatePhoneNumber(String phone, int id) throws DatabaseOperationException, InputValidationException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InputValidationException("Phone number cannot be empty.");
        }
        String query = "UPDATE customers SET phone = ? WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, phone);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update phone number.", e);
        }
    }

    @Override
    public void updateAddress(String address, int id) throws DatabaseOperationException, InputValidationException {
        if (address == null || address.trim().isEmpty()) {
            throw new InputValidationException("Address cannot be empty.");
        }
        String query = "UPDATE customers SET address = ? WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, address);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update address.", e);
        }
    }

    @Override
    public void CalculateTotalOrders(int id) throws DatabaseOperationException {
        String query = "SELECT no_of_orders FROM customers WHERE customer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int orders = rs.getInt("no_of_orders");
                    System.out.println("Customer ID: " + id + ", No. of Orders: " + orders);
                } else {
                    System.out.println("No customer found with ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to calculate total orders.", e);
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void validateCustomer(Customer customer) throws InputValidationException {
        if (customer == null) {
            throw new InputValidationException("Customer object is null.");
        }
        if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {
            throw new InputValidationException("First name is required.");
        }
        if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {
            throw new InputValidationException("Last name is required.");
        }
        if (!isValidEmail(customer.getEmail())) {
            throw new InputValidationException("Invalid email format.");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new InputValidationException("Phone number is required.");
        }
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            throw new InputValidationException("Address is required.");
        }
    }
}
