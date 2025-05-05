package dao;

import model.Product;
import util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private Connection connection;

    public ProductDao() {
        this.connection = DataBaseConnection.getConnection();
    }

    // Ürünleri listele
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Yeni ürün ekle
    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (name, description, price, stock) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.executeUpdate();
            System.out.println("Ürün başarıyla eklendi!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ürün sil
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Product WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Ürün başarıyla silindi!");
            } else {
                System.out.println("Ürün bulunamadı!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ürün güncelle
    public void updateProduct(Product product) {
        String sql = "UPDATE Product SET name = ?, description = ?, price = ?, stock = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setInt(5, product.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Ürün başarıyla güncellendi!");
            } else {
                System.out.println("Ürün bulunamadı!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
