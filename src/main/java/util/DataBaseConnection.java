package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/shopping_app";
    private static final String USER = "root"; // kendi kullanıcı adını yaz
    private static final String PASSWORD = "082003"; // kendi şifreni yaz

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Veritabanı bağlantısı başarılı!");
            } catch (SQLException e) {
                System.out.println("Veritabanı bağlantı hatası!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
