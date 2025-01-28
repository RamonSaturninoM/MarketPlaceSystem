import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is designed to handle every functionality for each user
 * it implements the MarketPlaceAccess interface and uses different 
 * methods to accomplish a smooth system.
 * 
 * @author ramonsaturnino
 */

public class User implements MarketPlaceAccess{
    private ArrayList<CartItem> cart;
    
    private Manager manager; // Reference to the Manager instance
    
    public User(Manager manager){
        cart = new ArrayList<>();
        this.manager = manager;
    }
    
    @Override
    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Logout\n");
            
            try{
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice! Please select a number between 1 and 6.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        viewInventory();
                        break;
                    case 2:
                        addItemToCart();
                        break;
                    case 3:
                        rmItemFromCart();
                        break;
                    case 4:
                        viewCart();
                        break;
                    case 5:
                        checkout();
                        break;
                    case 6:
                        return;
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();                
            }
        }
    }
    
    /**
     * The viewInventory() methods calls the inventory object from the manager class
     * to view the products in stock, I called the method using a manager class 
     * instance. The expected output is void, just displaying text.
     * @author ramonsaturnino
     */
    public void viewInventory() {
        manager.viewInventory(); // Call the Manager's viewInventory method
    }
    
    /**
     * This method takes an object "Product" and a double value "quantity"
     * to add the item to the ArrayList "Cart".
     * 
     * @param product
     * @param quantity 
     * @author ramonsaturnino
     */    
    public void addToCart(Product product, double quantity) {
        cart.add(new CartItem(product, quantity));
    }    
    
    /**
     * This is a helper function for addToCart(), since we first have to ask
     * the user what he/she wants to add, and then actually add to cart
     * because of that, is not recommendable to use addToCart() by itself
     * expecting parameters. 
     * 
     * @author ramonsaturnino
     */    
    public void addItemToCart(){
        Scanner scanner = new Scanner(System.in);                
        
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("name");
        name.toLowerCase();
        
        Product product = manager.searchProduct(name); //we got the product
        if (product != null) {
            System.out.print("Enter weight (kg): ");
            double weight = scanner.nextDouble();

            if (product.getWeight() < weight) {
                System.out.println("Item cannot be added, because you exceed the amount in inventory");
                return;
            } else {
                Product newProduct = null;

                if (product instanceof Vegetable) {
                    Vegetable veg = (Vegetable) product;
                    newProduct = new Vegetable(product.getName(), weight, product.calculatePrice(), veg.isOrganic());
                } else if (product instanceof Fruit) {
                    Fruit fruit = (Fruit) product;
                    newProduct = new Fruit(product.getName(), weight, product.calculatePrice(), fruit.getSeason());
                } else if (product instanceof Meat) {
                    Meat meat = (Meat) product;
                    newProduct = new Meat(product.getName(), weight, product.calculatePrice(), meat.getCutType());
                }

                if (newProduct != null) {
                    addToCart(newProduct, weight);
                    System.out.println(newProduct);
                    // update inventory
                    product.updateStock(weight);
                    //product.updateStock(weight); // Reduce stock in inventory
                    System.out.println("Product added successfully to cart!");
                }
                if (product.getWeight() == 0) {
                    manager.removeProduct(product);
                    System.out.println("Product removed from inventory as it is out of stock.");
            }
            }
        } else {
            System.out.println("Item not in stock.");
        } 
    }
    
    /**
     * This method takes an object "CartItem"
     * to remove the item from the ArrayList "Cart".
     * 
     * @param cartItem 
     * @author ramonsaturnino
     */
    public void removeFromCart(CartItem cartItem) {
        cart.remove(cartItem);
    }
    
    /**
     * Helper function for removeFromCart().
     * 
     * @author ramonsaturnino
     */
    public void rmItemFromCart(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product name to remove from cart: ");
        String name = scanner.nextLine();

        CartItem itemToRemove = null;

        // Find the item in the cart
        for (CartItem item : cart) {
            if (item.getProduct().getName().equalsIgnoreCase(name)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            // Restore stock in inventory
            Product product = itemToRemove.getProduct();
            double quantityToReturn = itemToRemove.getQuantity();

            Product inventoryProduct = manager.searchProduct(product.getName());
            if (inventoryProduct != null) {
                inventoryProduct.updateStock(-quantityToReturn);  // Add the quantity back to inventory
            }
            removeFromCart(itemToRemove);  // Remove from cart
            System.out.println("Successfully removed " + quantityToReturn + " kg of " + product.getName() + " from the cart and added back to inventory.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }
    
    /**
     * Displays every item in cart.
     * 
     * @author ramonsaturnino
     */
    public void viewCart() {
        System.out.println("Current cart:");
        for (CartItem item : cart) {
            System.out.println(item.getProduct().getName()
                    + ", Weight: " + item.getQuantity() + " kg, Price: $"
                    + String.format("%.2f", item.getTotalPrice()));
        }
    }
    
    /**
     * Handles payment confirmation.
     * 
     * @author ramonsaturnino
     */
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checking out.");
            return;
        }

        System.out.println("\n=== Checkout Summary ===");
        viewCart(); // Display all items in the cart

        // Calculate the total price
        double total = 0.0;
        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }
        System.out.println("Total: $" + String.format("%.2f", total));

        // Ask for confirmation
        Scanner scanner = new Scanner(System.in);
        System.out.print("Confirm checkout (Y/N)? ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("Y")) {
            // Clear the cart and finalize the purchase
            cart.clear();
            System.out.println("Thank you for your purchase! Your order total is $" + String.format("%.2f", total));
        } else {
            System.out.println("Checkout cancelled. Your cart remains unchanged.");
        }
    }
}
