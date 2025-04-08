package com.hexaware.electronicgadgets.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hexaware.electronicgadgets.dao.interfaces.Inventorydao;
import com.hexaware.electronicgadgets.entities.Inventory;
import com.hexaware.electronicgadgets.util.DatabaseConnection;

public class InventorydaoImpl implements Inventorydao {

    private Connection conn = DatabaseConnection.getConnection();

    @Override
    public void AddToInventory(Inventory inventory) {
        if (inventory == null || inventory.getProductId() == null || inventory.getLastStockUpdate() == null) {
            System.err.println("Invalid inventory data provided.");
            return;
        }

        String query = "INSERT INTO inventory VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, inventory.getInventoryId());
            ps.setInt(2, inventory.getProductId().getProductId());
            ps.setInt(3, inventory.getQuantityInStock());
            ps.setString(4, inventory.getLastStockUpdate());
            ps.executeUpdate();
            System.out.println("Inventory record inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error while inserting inventory record: " + e.getMessage());
        }
    }

    @Override
    public void viewAllProducts() {
        String query = "SELECT * FROM inventory";

        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(String.format("Inventory ID: %d | Product ID: %d | Quantity: %d | Last Update: %s",
                        rs.getInt("inventory_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantityinstock"),
                        rs.getString("laststockupdate")));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching inventory data: " + e.getMessage());
        }
    }

    @Override
    public void updateProductId(int productId, int inventoryId) {
        if (productId <= 0 || inventoryId <= 0) {
            System.err.println("Invalid productId or inventoryId.");
            return;
        }

        String query = "UPDATE inventory SET product_id = ? WHERE inventory_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            ps.setInt(2, inventoryId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record(s) updated.");
        } catch (SQLException e) {
            System.err.println("Error updating product ID: " + e.getMessage());
        }
    }

    @Override
    public void updateQuantityStock(int stock, int inventoryId) {
        if (stock < 0 || inventoryId <= 0) {
            System.err.println("Invalid stock or inventoryId.");
            return;
        }

        String query = "UPDATE inventory SET quantityinstock = ? WHERE inventory_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, stock);
            ps.setInt(2, inventoryId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record(s) updated.");
        } catch (SQLException e) {
            System.err.println("Error updating stock quantity: " + e.getMessage());
        }
    }

    @Override
    public void updatelastStockUpdate(String date, int inventoryId) {
        if (date == null || date.trim().isEmpty() || inventoryId <= 0) {
            System.err.println("Invalid date or inventoryId.");
            return;
        }

        String query = "UPDATE inventory SET laststockupdate = ? WHERE inventory_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, date);
            ps.setInt(2, inventoryId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record(s) updated.");
        } catch (SQLException e) {
            System.err.println("Error updating stock date: " + e.getMessage());
        }
    }

    @Override
    public void deleteFromInventory(int inventoryId) {
        if (inventoryId <= 0) {
            System.err.println("Invalid inventoryId.");
            return;
        }

        String query = "DELETE FROM inventory WHERE inventory_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, inventoryId);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record(s) deleted.");
        } catch (SQLException e) {
            System.err.println("Error deleting inventory record: " + e.getMessage());
        }
    }
}
