package service;

import models.Inventory;
import models.Product;
import models.User;

import java.util.*;

public class MarketplaceService {
    public static List<Product> productDB = new ArrayList<>();
    public static List<Inventory> cartList = new ArrayList<>();
    public static Map<String, List<Inventory>> orders = new HashMap<>();

    public static Product getProductById(long productId) {
        Product p = productDB.stream().filter(product -> product.getId() == productId).findFirst().orElseThrow(() -> {
            throw new RuntimeException("Product does not exist with the given id");
        });
        System.out.println(p);
        return p;
    }

    public static void addToCart(long productId, int quantity) {
        if (quantity <= 0) throw new RuntimeException("Invalid Quantity!");
        System.out.print("Adding item... \t\t");
        Product product = getProductById(productId);
        Optional<Inventory> cartItemOptional = cartList.stream().filter(inventory -> inventory.getProduct().getId() == product.getId()).findFirst();
        if (cartItemOptional.isPresent()) {
            Inventory inventoryItem = cartItemOptional.get();
            inventoryItem.setQuantity(inventoryItem.getQuantity() + quantity);
            System.out.println("Cart Updated");
        } else {
            cartList.add(new Inventory(product, quantity));
            System.out.println("Added new item to cart");
        }
    }

    public static void getCart() {
        System.out.println("Here is your current cart:");
        cartList.forEach(System.out::println);
    }

    public static void checkout() {
        if (cartList.isEmpty()) {
            throw new RuntimeException("Cart is empty, nothing to checkout");
        }
        if (orders.containsKey(UserService.currentUser)) {
            orders.get(UserService.currentUser).addAll(cartList);
        } else {
            List<Inventory> inventories = new ArrayList<>(cartList);
            orders.put(UserService.currentUser, inventories);
        }
        cartList.clear();
        System.out.println("Thank you for your purchase!");
    }

    public static void getOrderHistory() {
        if (!orders.containsKey(UserService.currentUser)) {
            System.out.println("You haven't ordered anything yet!");
            return;
        }
        System.out.println("Here is your order history:");
        List<Inventory> currentUserOrders = orders.get(UserService.currentUser);
        currentUserOrders.forEach(System.out::println);
    }
}
