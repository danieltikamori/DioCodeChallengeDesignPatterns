// Import necessary Java libraries
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define an interface for discount strategies
interface DiscountStrategy {
    // Method to apply a discount to the total price
    double applyDiscount(double total);
}

// Class implementing the DiscountStrategy interface for a 10% discount
class TenPercentDiscount implements DiscountStrategy {
    // Method to calculate the discounted total price
    @Override
    public double applyDiscount(double total) {
        return total * 0.9; // Apply a 10% discount
    }
}

// Class implementing the DiscountStrategy interface for free shipping
class FreeShipping implements DiscountStrategy {
    // Method to apply free shipping and return the total price as is
    @Override
    public double applyDiscount(double total) {
        System.out.println("Free shipping applied."); // Print message
        return total; // Return the total price without any changes
    }
}

// Class representing a shopping cart
class ShoppingCart {
    private List<Double> products = new ArrayList<>(); // List to store product prices
    private DiscountStrategy discountStrategy; // Selected discount strategy

    // Method to add a product price to the shopping cart
    public void addProduct(double price) {
        products.add(price); // Add the product price to the list
    }

    // Method to set the discount strategy for the shopping cart
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy; // Set the chosen discount strategy
    }

    // Method to calculate the total price of all products in the cart
    public double calculateTotal() {
        double subtotal = 0.0; // Initialize subtotal to 0
        for (double price : products) {
            subtotal += price; // Calculate the total of all product prices
        }
        // Apply discount based on the chosen strategy
        if (discountStrategy != null) {
            return discountStrategy.applyDiscount(subtotal); // Apply the chosen discount
        } else {
            return subtotal; // No discount strategy set, return subtotal as is
        }
    }
}

// Main class for running the shopping cart application
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        DiscountStrategy tenPercentDiscount = new TenPercentDiscount(); // Create 10% discount strategy
        DiscountStrategy freeShipping = new FreeShipping(); // Create free shipping strategy

        ShoppingCart cart = new ShoppingCart(); // Create a new shopping cart

        String productName = scanner.nextLine(); // Read product name (not used in this code)
        double productPrice = scanner.nextDouble(); // Read product price
        cart.addProduct(productPrice); // Add the product price to the cart

        int strategyChoice = scanner.nextInt(); // Read user's choice of discount strategy

        // Set the discount strategy based on user input
        switch (strategyChoice) {
            case 1:
                cart.setDiscountStrategy(tenPercentDiscount); // Apply 10% discount
                break;
            case 2:
                cart.setDiscountStrategy(freeShipping); // Apply free shipping
                break;
            default:
                System.out.println("Invalid choice. No discount applied."); // Inform user of invalid choice
        }

        // Calculate and display the total price after applying the discount
        double totalPrice = cart.calculateTotal(); // Calculate total price after discount
        System.out.println("Total price: $ " + totalPrice); // Display the total price
    }
}