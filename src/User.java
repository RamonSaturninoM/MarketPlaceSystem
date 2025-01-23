import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ramonsaturnino
 */
public class User implements MarketPlaceAccess{
    private ArrayList<CartItem> cart;
    
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
                    viewCart();
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
    
    public void addToCart(Product product, double quantity) {
        cart.add(new CartItem(product, quantity));
    }    
    
    public void addItemToCart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What item would you like to add to cart? ");
    }
    
    public void removeFromCart(Product product) {
        // Implement remove logic
    }
    
    public void viewCart() {
        // Display cart contents
    }
    
    public void checkout() {
        // Implement checkout logic
    }
}
