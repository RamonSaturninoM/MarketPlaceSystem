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
        if(!product.equals(null)){
            System.out.print("Enter weight (kg): ");
            double weight = scanner.nextDouble();
            if(product.weight < weight){
                System.out.println("Item cannot be added, because you exceed the amount in inventory");
                return;
            }else{
                if (product != null) {
                addToCart(product, weight);  // Now calling the correct method with a Product parameter
                System.out.println("Product added successfully!");
                }
            }
        
//            System.out.print("Enter product type (1: Vegetable, 2: Fruit, 3: Meat): ");
//            int type = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//            
//           // product = null;
//            switch (type) {
//                case 1:
//                    System.out.print("Is organic? (true/false): ");
//                    boolean isOrganic = scanner.nextBoolean();
//                    product = new Vegetable(name, weight, estimatePrice(product, weight), isOrganic);
//                    break;
//                case 2:
//                    System.out.print("Enter season: ");
//                    String season = scanner.nextLine();
//                    product = new Fruit(name, weight, estimatePrice(product, weight), season);
//                    break;
//                case 3:
//                    System.out.print("Enter cut type: ");
//                    String cutType = scanner.nextLine();
//                    product = new Meat(name, weight, estimatePrice(product, weight), cutType);
//                    break;            
//            }
            
            
        }        
    }
    
    public void removeFromCart(Product product) {
        // Implement remove logic
    }
    
    public void viewCart() {
        System.out.println("Current cart: " + cart);
    }
    
    public void checkout() {
        // Implement checkout logic
    }
}
