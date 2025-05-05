package controller;

import dao.CartDao;
import dao.ProductDao;
import model.CartItem;
import model.Product;
import model.User;

import java.util.List;
import java.util.Scanner;

public class CartController {

    private CartDao cartDao;
    private ProductDao productDao;
    private Scanner scanner;
    private User user;

    public CartController(User user) {
        this.cartDao = new CartDao();
        this.productDao = new ProductDao();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    public void addToCart() {
        System.out.print("Sepete eklemek istediğiniz ürün ID'sini girin: ");
        int productId = scanner.nextInt();
        System.out.print("Kaç adet eklemek istiyorsunuz? ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // dummy

        CartItem item = new CartItem(0, user.getId(), productId, quantity);
        cartDao.addToCart(item);
    }

    public void viewCart() {
        List<CartItem> items = cartDao.getCartItems(user.getId());
        System.out.println("\n--- Sepetiniz ---");
        for (CartItem item : items) {
            Product product = productDao.getAllProducts().stream()
                    .filter(p -> p.getId() == item.getProductId())
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                System.out.println("Ürün: " + product.getName() +
                        ", Miktar: " + item.getQuantity() +
                        ", Fiyat: " + product.getPrice() * item.getQuantity());
            }
        }
    }

    public void checkout() {
        List<CartItem> items = cartDao.getCartItems(user.getId());
        double total = 0;
        for (CartItem item : items) {
            Product product = productDao.getAllProducts().stream()
                    .filter(p -> p.getId() == item.getProductId())
                    .findFirst()
                    .orElse(null);
            if (product != null) {
                total += product.getPrice() * item.getQuantity();
            }
        }
        System.out.println("\nToplam Tutar: " + total + " TL");
        System.out.println("Sipariş tamamlandı!");
        cartDao.clearCart(user.getId());
    }
}
