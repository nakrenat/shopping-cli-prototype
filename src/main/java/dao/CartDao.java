package dao;

import model.CartItem;
import util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    private Connection connection;

    public CartDao() {
        this.connection = DataBaseConnection.getConnection();
    }

    // Sepete ürün ekle
    public void addToCart(CartItem item) {
        String sql = "INSERT INTO CartItem (userId, productId, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, item.getUserId());
            pstmt.setInt(2, item.getProductId());
            pstmt.setInt(3, item.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Ürün sepete eklendi!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcının sepetini getir
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM CartItem WHERE userId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CartItem item = new CartItem(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity")
                );
                cartItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Sepeti temizle (siparişi tamamlayınca)
    public void clearCart(int userId) {
        String sql = "DELETE FROM CartItem WHERE userId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
