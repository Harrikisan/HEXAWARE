package com.hexaware.electronicgadgets.dao.implementations;

import java.sql.*;
import java.util.ArrayList;

import com.hexaware.electronicgadgets.dao.interfaces.Ordersdao;
import com.hexaware.electronicgadgets.entities.Orders;
import com.hexaware.electronicgadgets.entities.Customer;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.util.DatabaseConnection;

public class OrdersdaoImpl implements Ordersdao {

    private Connection conn = DatabaseConnection.getConnection();

    @Override
    public void addOrder(Orders order) throws DatabaseOperationException {
        if (order == null || order.getCustomer() == null) {
            throw new IllegalArgumentException("Order or customer cannot be null.");
        }

        String query = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getCustomer().getCustomerId());
            ps.setString(3, order.getOrderDate());
            ps.setInt(4, order.getTotalAmount());
            ps.setString(5, order.getStatus());

            ps.executeUpdate();
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to add order.", e);
        }
    }

    @Override
    public void viewAllOrders() throws DatabaseOperationException {
        ArrayList<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));

                Orders order = new Orders(
                        rs.getInt("order_id"),
                        customer,
                        rs.getString("order_date"),
                        rs.getInt("total_amount"),
                        rs.getString("status")
                );
                orders.add(order);
            }

            for (Orders o : orders) {
                System.out.println("Order ID: " + o.getOrderId() +
                        " | Customer ID: " + o.getCustomer().getCustomerId() +
                        " | Order Date: " + o.getOrderDate() +
                        " | Total Amount: " + o.getTotalAmount() +
                        " | Status: " + o.getStatus());
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to retrieve orders.", e);
        }
    }

    @Override
    public void updateCustomerId(int customerId, int orderId) throws DatabaseOperationException {
        if (customerId <= 0 || orderId <= 0) {
            throw new IllegalArgumentException("Customer ID and Order ID must be positive.");
        }

        String query = "UPDATE orders SET customer_id = ? WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ps.setInt(2, orderId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseOperationException("Order ID not found.");
            }

            System.out.println("Customer ID updated successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update customer ID.", e);
        }
    }

    @Override
    public void updateOrderDate(String date, int orderId) throws DatabaseOperationException {
        if (date == null || date.isEmpty() || orderId <= 0) {
            throw new IllegalArgumentException("Invalid date or order ID.");
        }

        String query = "UPDATE orders SET order_date = ? WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, date);
            ps.setInt(2, orderId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseOperationException("Order ID not found.");
            }

            System.out.println("Order date updated successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update order date.", e);
        }
    }

    @Override
    public void updateTotalAmount(int amount, int orderId) throws DatabaseOperationException {
        if (amount < 0 || orderId <= 0) {
            throw new IllegalArgumentException("Invalid amount or order ID.");
        }

        String query = "UPDATE orders SET total_amount = ? WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, amount);
            ps.setInt(2, orderId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseOperationException("Order ID not found.");
            }

            System.out.println("Total amount updated successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update total amount.", e);
        }
    }

    @Override
    public void updateStatus(String status, int orderId) throws DatabaseOperationException {
        if (status == null || status.isEmpty() || orderId <= 0) {
            throw new IllegalArgumentException("Invalid status or order ID.");
        }

        String query = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseOperationException("Order ID not found.");
            }

            System.out.println("Status updated successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update status.", e);
        }
    }

    @Override
    public void cancelOrder(int orderId) throws DatabaseOperationException {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }

        String query = "DELETE FROM orders WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DatabaseOperationException("Order ID not found.");
            }

            System.out.println("Order cancelled successfully.");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to cancel order.", e);
        }
    }

    @Override
    public void calculateTotalAmount(int orderId) throws DatabaseOperationException {
        // Placeholder logic (normally you'd calculate based on order_items or similar)
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }

        String query = "SELECT total_amount FROM orders WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total_amount");
                System.out.println("Total amount for order ID " + orderId + " is ₹" + total);
            } else {
                throw new DatabaseOperationException("Order ID not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to calculate total amount.", e);
        }
    }
}
