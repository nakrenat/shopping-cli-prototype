package controller;

import controller.ProductController;
import controller.UserController;
import model.User;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        ProductController productController = new ProductController();

        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n=== Hoş Geldiniz ===");
            System.out.println("1. Giriş Yap");
            System.out.println("2. Kayıt Ol");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = new java.util.Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    loggedInUser = userController.login();
                    break;
                case 2:
                    userController.register();
                    break;
                case 3:
                    System.out.println("Çıkılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }

        // Giriş yapıldıysa ürün menüsüne geç
        productController.start();
    }
}
