package controller;

import dao.UserDao;
import model.User;

import java.util.Scanner;

public class UserController {

    private UserDao userDao;
    private Scanner scanner;

    public UserController() {
        this.userDao = new UserDao();
        this.scanner = new Scanner(System.in);
    }

    public User register() {
        System.out.println("\n=== Kayıt Ol ===");
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        User user = new User(0, username, password, false);
        boolean success = userDao.registerUser(user);

        if (success) {
            System.out.println("Kayıt başarılı! Şimdi giriş yapabilirsiniz.");
            return user;
        } else {
            System.out.println("Kayıt başarısız oldu.");
            return null;
        }
    }

    public User login() {
        System.out.println("\n=== Giriş Yap ===");
        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();
        System.out.print("Şifre: ");
        String password = scanner.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null) {
            System.out.println("Giriş başarılı! Hoş geldin " + user.getUsername() + "!");
        } else {
            System.out.println("Giriş başarısız!");
        }
        return user;
    }
}
