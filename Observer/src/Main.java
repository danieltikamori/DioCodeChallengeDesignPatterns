// Importing necessary classes and interfaces
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Interface Observer
// Defines the method that observers must implement
interface Observer {
    void update(String productName);
}

// Classe concreta Observer
// Implements the Observer interface and provides the implementation for the update method
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String productName) {
        // Prints a notification when a new product is added
        System.out.println("Notificacao recebida: Novo produto adicionado - " + productName);
    }
}

// Interface Observable
// Defines the methods for registering and notifying observers
interface CatalogObservable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String productName);
}

// Classe concreta Observable
// Implements the CatalogObservable interface and provides the implementation for the methods
class Catalog implements CatalogObservable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        // Adds an observer to the list of observers
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        // Removes an observer from the list of observers
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String productName) {
        // Notifies all the observers by calling their update method
        if (productName != null) {
            for (Observer observer : observers) {
                observer.update(productName);
            }
        }
    }

    public void addProduct(String name, String description, double price) {
        // Adds a product and notifies the observers
        notifyObservers(name);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating the catalog and user
        Catalog catalog = new Catalog();
        User user = new User("Usuário 1");

        // Registering the user in the catalog
        catalog.addObserver(user);

        // Adding a new product
        String name = scanner.nextLine();
        String description = scanner.nextLine();
        double price;
        while (true) {
            try {
                price = scanner.nextDouble();
                break;  // Exit the loop if valid input is provided
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.next();  // Clear the invalid input
            }
        }

        scanner.nextLine(); // Consume the newline character after nextDouble
        String subscribeChoice = scanner.nextLine();

        if (subscribeChoice.equalsIgnoreCase("cancelar" ) || subscribeChoice.equalsIgnoreCase("N")) {
            // Cancelling the subscription
            catalog.removeObserver(user);
            System.out.println("Programa Encerrado.");
        }
        catalog.addProduct(name, description, price);

        // To prevent resource leaks, you should close the Scanner object after its use. You can do this by adding the following line at the end of the main method:
        scanner.close();
    }
}