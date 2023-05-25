package service;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    public static List<User> userDB = new ArrayList<>();
    public static boolean isLoggedIn = false;
    public static String currentUser = null;

    public static void createUser(String name, String email, String password) {
        if (userDB.stream().anyMatch(user -> Objects.equals(user.getEmail(), email)))
            throw new RuntimeException("email is already registered!");
        userDB.add(new User(name, email, password));
        System.out.println("You are registered!");
    }

    public static void loginUser(String email, String password) {
        if (userDB.stream().anyMatch(user -> Objects.equals(user.getEmail(), email) && Objects.equals(user.getPassword(), password))) {
            System.out.println("You are logged in!");
            return;
        }
        throw new RuntimeException("Invalid Credentials!");
    }

    public static void logout() {
        MarketplaceService.cartList.clear();
        isLoggedIn = false;
        currentUser = null;
        System.out.println("You are logged out!");
    }
}
