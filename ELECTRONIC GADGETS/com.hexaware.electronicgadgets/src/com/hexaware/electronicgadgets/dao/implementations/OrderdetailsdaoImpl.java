package com.hexaware.electronicgadgets.dao.implementations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.electronicgadgets.dao.interfaces.Orderdetailsdao;
import com.hexaware.electronicgadgets.entities.Orderdetails;
import com.hexaware.electronicgadgets.entities.Orders;
import com.hexaware.electronicgadgets.entities.Products;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.util.DatabaseConnection;

public class OrderdetailsdaoImpl implements Orderdetailsdao {

    private Connection conn = DatabaseConnection.getConnection();

    @Override
    public void addOrderInfo(Orderdetails orderdetail) throws DatabaseOperationException {
        if (orderdetail == null || orderdetail.getOrderId() == null || orderdetail.getProductId() == null) {
            throw new IllegalArgumentException("Order detail, order ID, or product ID cannot be null.");
        }

        String query = "INSERT INTO orderdetails VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderdetail.getOrderDetailId());
            ps.setInt(2, orderdetail.getOrderId().getOrderId());
            ps.setInt(3, orderdetail.getProductId().getProductId());
            ps.setInt(4, orderdetail.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to add order detail info.", e);
        }
    }

    @Override
    public void viewAllInfo() throws DatabaseOperationException {
        String query = "SELECT * FROM orderdetails";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            List<Orderdetails> list = new ArrayList<>();

            while (rs.next()) {
                Orderdetails detail = new Orderdetails(
                        rs.getInt("orderdetailid"),
                        new Orders(rs.getInt("order_id")),
                        new Products(rs.getInt("product_id")),
                        rs.getInt("quantity")
                );
                list.add(detail);
            }

            for (Orderdetails od : list) {
                System.out.println("Info ID: " + od.getOrderDetailId()
                        + ", Order ID: " + od.getOrderId().getOrderId()
                        + ", Product ID: " + od.getProductId().getProductId()
                        + ", Quantity: " + od.getQuantity());
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to retrieve order details.", e);
        }
    }

    @Override
    public void updateOrderId(int orderId, int infoId) throws DatabaseOperationException {
        String query = "UPDATE orderdetails SET order_id = ? WHERE orderdetailid = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderId);
            ps.setInt(2, infoId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No record found to update.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update order ID.", e);
        }
    }

    @Override
    public void upadteProductId(int productId, int infoId) throws DatabaseOperationException {
        String query = "UPDATE orderdetails SET product_id = ? WHERE orderdetailid = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, infoId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No record found to update.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update product ID.", e);
        }
    }

    @Override
    public void updateQuantity(int quantity, int infoId) throws DatabaseOperationException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }

        String query = "UPDATE orderdetails SET quantity = ? WHERE orderdetailid = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, quantity);
            ps.setInt(2, infoId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No record found to update.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update quantity.", e);
        }
    }

    @Override
    public void calculateSubTotal(Orderdetails orderdetail) throws DatabaseOperationException {
        if (orderdetail == null || orderdetail.getProductId() == null) {
            throw new IllegalArgumentException("Order detail or product ID cannot be null.");
        }

        String query = "SELECT price FROM products WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderdetail.getProductId().getProductId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int price = rs.getInt("price");
                    int subtotal = price * orderdetail.getQuantity();
                    System.out.println("Subtotal: " + subtotal);
                } else {
                    System.out.println("Product not found.");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to calculate subtotal.", e);
        }
    }

    @Override
    public void addDiscount(int amount, int infoId) throws DatabaseOperationException {
        if (amount < 0) {
            throw new IllegalArgumentException("Discount amount cannot be negative.");
        }

        String query = "UPDATE orders SET total_amount = total_amount - ? WHERE orderid = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, amount);
            ps.setInt(2, infoId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No matching info ID found to apply discount.");
            } else {
                System.out.println("Discount applied successfully.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to apply discount.", e);
        }
    }

    @Override
    public void deleteInfo(int infoId) throws DatabaseOperationException {
        String query = "DELETE FROM orderdetails WHERE orderdetailid = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, infoId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No info found to delete.");
            } else {
                System.out.println("Deleted successfully.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete order detail.", e);
        }
    }
}
