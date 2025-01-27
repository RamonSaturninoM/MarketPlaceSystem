import java.util.ArrayList;
import java.util.Scanner;

/**
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
    
    public void viewInventory() {
        manager.viewInventory(); // Call the Manager's viewInventory method
    }
    
    public void addToCart(Product product, double quantity) {
        cart.add(new CartItem(product, quantity));
    }    
    
    //Following code is experimental; Will delete and implement in product or manager class.
    public double estimatePrice(Product prod, double weight){
            double expectedPrice = prod.pricePerKg * weight;
            return expectedPrice;
        }
    // Stops here
    
    public void addItemToCart(){
        Scanner scanner = new Scanner(System.in);                
        
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        name.toLowerCase();
        
        Product product = manager.searchProduct(name); //we got the product
        System.out.println(product);
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
    
    public void removeFromCart(CartItem cartItem) {
        cart.remove(cartItem);
    }
    
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
//            } else {
//                // If product was removed from inventory, re-add it
//                manager.addNewProduct(product.getName(), quantityToReturn, product.calculatePrice(), product.isOrganic());
//            }

            }removeFromCart(itemToRemove);  // Remove from cart
            System.out.println("Successfully removed " + quantityToReturn + " kg of " + product.getName() + " from the cart and added back to inventory.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void viewCart() {
        System.out.println("Current cart:");
        for (CartItem item : cart) {
            System.out.println(item.getProduct().getName()
                    + ", Weight: " + item.getQuantity() + " kg, Total Price: $"
                    + String.format("%.2f", item.getTotalPrice()));
        }
    }
    
    public void checkout() {
        // Implement checkout logic
    }
}
