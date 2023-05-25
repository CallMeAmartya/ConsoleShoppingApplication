import models.Product;
import service.MarketplaceService;
import service.UserService;

import java.io.IOException;
import java.util.*;

public class Main {
    public static final String YOU_ARE_NOT_LOGGED_IN = "You are not logged in!";

    public static void main(String[] args) throws IOException {
        initializeProductDB();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String s = scanner.nextLine();
                try {
                    parse(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void initializeProductDB() {
        MarketplaceService.productDB.add(new Product(1, "ABC", 10));
        MarketplaceService.productDB.add(new Product(2, "DEF", 15));
        MarketplaceService.productDB.add(new Product(3, "XYZ", 25));
    }

    private static void parse(String s) {
        List<String> commandList = List.of(s.trim().split(" "));
        String command = commandList.get(0).toUpperCase(Locale.ROOT);
        switch (command) {
            case "LOGIN":
                UserService.loginUser(commandList.get(1), commandList.get(2));
                UserService.currentUser = commandList.get(1);
                UserService.isLoggedIn = true;
                break;
            case "REGISTER":
                UserService.createUser(commandList.get(1), commandList.get(2), commandList.get(3));
                break;
            case "ADD_TO_CART":
                if (UserService.isLoggedIn) MarketplaceService.addToCart(Long.parseLong(commandList.get(1)), Integer.parseInt(commandList.get(2)));
                else throw new RuntimeException(YOU_ARE_NOT_LOGGED_IN);
                break;
            case "VIEW_CART":
                if (UserService.isLoggedIn) MarketplaceService.getCart();
                else throw new RuntimeException(YOU_ARE_NOT_LOGGED_IN);
                break;
            case "CHECKOUT":
                if (UserService.isLoggedIn) MarketplaceService.checkout();
                else throw new RuntimeException(YOU_ARE_NOT_LOGGED_IN);
                break;
            case "GET_ORDER_HISTORY":
                if (UserService.isLoggedIn) MarketplaceService.getOrderHistory();
                else throw new RuntimeException(YOU_ARE_NOT_LOGGED_IN);
                break;
            case "LOGOUT":
                if (UserService.isLoggedIn) UserService.logout();
                else throw new RuntimeException(YOU_ARE_NOT_LOGGED_IN);
                break;
            case "EXIT":
                exitApp();
                break;
            default:
                throw new RuntimeException("Invalid Command");
        }
    }


    public static void exitApp() {
        System.exit(0);
    }


}
