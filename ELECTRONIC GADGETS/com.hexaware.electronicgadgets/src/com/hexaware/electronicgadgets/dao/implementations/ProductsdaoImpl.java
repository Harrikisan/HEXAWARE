package com.hexaware.electronicgadgets.dao.implementations;

import java.sql.*;
import java.util.ArrayList;

import com.hexaware.electronicgadgets.dao.interfaces.Productsdao;
import com.hexaware.electronicgadgets.entities.Products;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.util.DatabaseConnection;

public class ProductsdaoImpl implements Productsdao {

    private Connection conn = DatabaseConnection.getConnection();

    @Override
    public void addProduct(Products product) throws DatabaseOperationException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        String query = "INSERT INTO products VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setInt(4, (int) product.getPrice());
            ps.setString(5, product.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to add product with ID: " + product.getProductId(), e);
        }
    }

    @Override
    public void listAllProducts() throws DatabaseOperationException {
        ArrayList<Products> productsList = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(query)) {
            while (rs.next()) {
                productsList.add(new Products(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("category")
                ));
            }

            for (Products p : productsList) {
                System.out.println(
                        "ProductID:"+p.getProductId()+ "| Name:"+p.getProductName()+
                        "| Description:"+ p.getDescription()+"| Price:"+p.getPrice()+
                        "| Category:"+ p.getCategory()
                );
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to retrieve product list.", e);
        }
    }

    @Override
    public void updateProductName(String name, int productId) throws DatabaseOperationException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }

        String query = "UPDATE products SET product_name = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DatabaseOperationException("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update product name.", e);
        }
    }

    @Override
    public void updateDescription(String description, int productId) throws DatabaseOperationException {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }

        String query = "UPDATE products SET description = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, description);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DatabaseOperationException("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update product description.", e);
        }
    }

    @Override
    public void updateprice(int price, int productId) throws DatabaseOperationException {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }

        String query = "UPDATE products SET price = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, price);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DatabaseOperationException("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update product price.", e);
        }
    }

    @Override
    public void updateCategory(String category, int productId) throws DatabaseOperationException {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }

        String query = "UPDATE products SET category = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, category);
            ps.setInt(2, productId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DatabaseOperationException("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update product category.", e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws DatabaseOperationException {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new DatabaseOperationException("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete product with ID: " + productId, e);
        }
    }
}
