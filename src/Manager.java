import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is in charge to run every functionality per each Manager
 * account and display the options menu.
 * 
 * @author ramonsaturnino
 */

public class Manager implements MarketPlaceAccess{
    private ArrayList<Product> inventory;
    private static Manager instance;
    
    public Manager(){
        inventory = new ArrayList<>();
    }
    
    @Override
    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Inventory");
            System.out.println("3. Remove Product");
            System.out.println("4. Logout\n");
            
            try{
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice! Please select a number between 1 and 4.");
                    continue;
                }
                scanner.nextLine(); // consume newline            
                switch (choice) {
                    case 1:
                        addNewProduct();
                        break;
                    case 2:
                        viewInventory();
                        break;
                    case 3:
                        removeProdHelper();
                        break;
                    case 4:
                        return;
            }
            }catch(java.util.InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
    
    public void addProduct(Product product){
        inventory.add(product);
    }
    
    private void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        name.toLowerCase();
        
        System.out.print("Enter weight (kg): ");
        double weight = scanner.nextDouble();
        
        System.out.print("Enter price per kg: ");
        double pricePerKg = scanner.nextDouble();
        
        System.out.print("Enter product type (1: Vegetable, 2: Fruit, 3: Meat): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Product product = null;
        switch (type) {
            case 1:
                System.out.print("Is organic? (true/false): ");
                boolean isOrganic = scanner.nextBoolean();
                product = new Vegetable(name, weight, pricePerKg, isOrganic);
                break;
            case 2:
                System.out.print("Enter season: ");
                String season = scanner.nextLine();
                season.toLowerCase();
                product = new Fruit(name, weight, pricePerKg, season);
                break;
            case 3:
                System.out.print("Enter cut type: ");
                String cutType = scanner.nextLine();
                cutType.toLowerCase();
                product = new Meat(name, weight, pricePerKg, cutType);
                break;
        }
        
        if (product != null) {
            addProduct(product);  // Now calling the correct method with a Product parameter
            System.out.println("Product added successfully!");
        }
    }
    
    public void removeProduct(Product product){
        inventory.remove(product);
    }
    
    public void removeProdHelper(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
    
        Product productToRemove = searchProduct(name);
        if (productToRemove != null) {
            removeProduct(productToRemove);
            System.out.println("Product removed successfully!");
        }   else {
            System.out.println("Product not found!");
        }
    }
    
    public Product searchProduct(String name){
        // Find and remove the product from inventory by name
        Product productToRemove = null;
        
        for (Product product : inventory) {
            if (product.getName().equals(name)) {
            productToRemove = product;
            break;
            }
        }
        return productToRemove;
    }
    
    public void viewInventory(){
        if (inventory.isEmpty()) {
        System.out.println("Current Inventory: No products available.");
        } else {
            System.out.println("Current Inventory: ");
            for (Product product : inventory) {
                System.out.println(product.toString()); // Assuming toString() is overridden in Product subclasses
            }
        }
    }
    
    public void adjustWeight(Product product, double weight){
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
    
        Product productToUpdate = searchProduct(name);
    
        if (productToUpdate != null) {
            System.out.println("Current weight: " + productToUpdate.getWeight() + "kg");
            System.out.print("Enter weight to subtract (kg): ");
            double weightToSubtract = scanner.nextDouble();

            if (weightToSubtract <= 0) {
                System.out.println("Error: Weight must be positive!");
            } else if (weightToSubtract > productToUpdate.getWeight()) {
                System.out.println("Error: Cannot subtract more than available weight!");
            } else if (weightToSubtract == productToUpdate.getWeight()) {
                removeProduct(productToUpdate);
                System.out.println("Product removed as weight is now 0!");
            } else {
                productToUpdate.setWeight(productToUpdate.getWeight() - weightToSubtract);
                System.out.printf("Weight updated! New weight: %.2fkg%n", productToUpdate.getWeight());
            }
        } else {
            System.out.println("Product not found!");
        }
    }
    
    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager(); // Create the instance if it doesn't exist
        }
        return instance; // Return the singleton instance
    }

}
