package util;

import util.DataBaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DataBaseConnection.getConnection();
        if (conn != null) {
            System.out.println("Bağlantı başarılı çalışıyor!");
        } else {
            System.out.println("Bağlantı başarısız.");
        }
    }
}
