package controller;

import dao.ProductDao;
import model.Product;
import model.User;

import java.util.List;
import java.util.Scanner;

public class ProductController {

    private ProductDao productDao;
    private Scanner scanner;

    public ProductController() {
        this.productDao = new ProductDao();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Ürün Yönetimi ===");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Yeni Ürün Ekle");
            System.out.println("3. Ürün Sil");
            System.out.println("4. Ürün Güncelle");
            System.out.println("5. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // dummy nextLine() to consume newline

            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    System.out.println("Çıkılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    private void listProducts() {
        List<Product> products = productDao.getAllProducts();
        System.out.println("\n--- Ürün Listesi ---");
        for (Product p : products) {
            System.out.println("ID: " + p.getId() + ", İsim: " + p.getName() +
                    ", Fiyat: " + p.getPrice() + ", Stok: " + p.getStock());
        }
    }

    private void addProduct() {
        System.out.print("Ürün ismi: ");
        String name = scanner.nextLine();
        System.out.print("Açıklama: ");
        String description = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        System.out.print("Stok: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // dummy nextLine

        Product product = new Product(0, name, description, price, stock);
        productDao.addProduct(product);
    }

    private void deleteProduct() {
        System.out.print("Silmek istediğiniz ürünün ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // dummy nextLine
        productDao.deleteProduct(id);
    }

    private void updateProduct() {
        System.out.print("Güncellemek istediğiniz ürünün ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // dummy nextLine

        System.out.print("Yeni ürün ismi: ");
        String name = scanner.nextLine();
        System.out.print("Yeni açıklama: ");
        String description = scanner.nextLine();
        System.out.print("Yeni fiyat: ");
        double price = scanner.nextDouble();
        System.out.print("Yeni stok: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // dummy nextLine

        Product product = new Product(id, name, description, price, stock);
        productDao.updateProduct(product);
    }
    public void startCustomer(User user) {
        CartController cartController = new CartController(user);

        while (true) {
            System.out.println("\n=== Alışveriş Paneli ===");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Sepete Ürün Ekle");
            System.out.println("3. Sepeti Görüntüle");
            System.out.println("4. Ödeme Yap ve Siparişi Tamamla");
            System.out.println("5. Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    cartController.addToCart();
                    break;
                case 3:
                    cartController.viewCart();
                    break;
                case 4:
                    cartController.checkout();
                    break;
                case 5:
                    System.out.println("Çıkılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
    public void startAdmin(User user) {
        CartController cartController = new CartController(user);

        while (true) {
            System.out.println("\n=== Admin Paneli ===");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Yeni Ürün Ekle");
            System.out.println("3. Ürün Sil");
            System.out.println("4. Ürün Güncelle");
            System.out.println("5. Sepete Ürün Ekle");
            System.out.println("6. Sepeti Görüntüle");
            System.out.println("7. Ödeme Yap ve Siparişi Tamamla");
            System.out.println("8. Çıkış");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: listProducts(); break;
                case 2: addProduct(); break;
                case 3: deleteProduct(); break;
                case 4: updateProduct(); break;
                case 5: cartController.addToCart(); break;
                case 6: cartController.viewCart(); break;
                case 7: cartController.checkout(); break;
                case 8: return;
                default: System.out.println("Geçersiz seçim!");
            }
        }
    }


}
