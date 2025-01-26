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
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Logout\n");
            
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
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    return;
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
                    //product.updateStock(weight); // Reduce stock in inventory
                    System.out.println("Product added successfully to cart!");
                }
            }
        } else {
            System.out.println("Item not in stock.");
        } 
    }
    
    public void removeFromCart(Product product) {
        // Implement remove logic
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
