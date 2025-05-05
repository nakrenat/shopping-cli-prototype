package dao;

import model.User;
import util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection connection;

    public UserDao() {
        this.connection = DataBaseConnection.getConnection();
    }

    // Kullanıcı kaydet
    public boolean registerUser(User user) {
        String sql = "INSERT INTO User (username, password, isAdmin) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setBoolean(3, user.isAdmin());
            pstmt.executeUpdate();
            System.out.println("Kullanıcı başarıyla kayıt oldu!");
            return true;
        } catch (SQLException e) {
            System.out.println("Kayıt sırasında hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Kullanıcı giriş
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                boolean isAdmin = rs.getBoolean("isAdmin");
                return new User(id, username, password, isAdmin);
            } else {
                System.out.println("Kullanıcı bulunamadı veya şifre yanlış!");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
