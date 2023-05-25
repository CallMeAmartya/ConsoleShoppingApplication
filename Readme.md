# Java Console Shopping Application

This is a Java console-based shopping application that allows users to register, login, add products to their cart, view their cart, checkout, and view their order history.

## Getting Started

To run the application, follow these steps:

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
4. Run the `Main` class.

## Usage

The application supports the following commands:

- `LOGIN <email> <password>`: Logs in the user with the specified email and password.
- `REGISTER <name> <email> <password>`: Creates a new user with the provided name, email, and password.
- `ADD_TO_CART <productId> <quantity>`: Adds the specified quantity of a product to the user's cart.
- `VIEW_CART`: Displays the current contents of the user's cart.
- `CHECKOUT`: Processes the checkout, finalizes the order, and clears the cart.
- `GET_ORDER_HISTORY`: Shows the user's order history.
- `LOGOUT`: Logs out the current user.
- `EXIT`: Exits the application.

## Data Structure

The application uses the following data structures:

- `User`: Represents a user with a name, email, and password.
- `Product`: Represents a product with an ID, name, and price.
- `Inventory`: Represents an item in the cart, linking a product with a quantity.

## Notes

- Users must be logged in to perform certain actions like adding items to the cart, checking out, viewing the cart, etc.
- Each user has their own cart and order history.

Feel free to explore and modify the code to fit your needs!