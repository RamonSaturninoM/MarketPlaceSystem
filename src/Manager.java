import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ramonsaturnino
 */
public class Manager implements MarketPlaceAccess{
    private ArrayList<Product> inventory;
    
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
            
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
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
        }
    }
    
    public void addProduct(Product product){
        inventory.add(product);
    }
    
    private void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        
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
                product = new Fruit(name, weight, pricePerKg, season);
                break;
            case 3:
                System.out.print("Enter cut type: ");
                String cutType = scanner.nextLine();
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
        
        System.out.print("Enter weight (kg): ");
        double weight = scanner.nextDouble();
        
//        System.out.print("Enter price per kg: ");
//        double pricePerKg = scanner.nextDouble();
        
        System.out.print("Enter product type (1: Vegetable, 2: Fruit, 3: Meat): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (name != null) {
            removeProduct(name);  // Now calling the correct method with a Product parameter
            System.out.println("Product removed successfully!");
        }
    }
    
    public Product searchProduct(String name){
        return null;
    }
    
    public void viewInventory(){
        System.out.println(inventory);
    }
    
    public void adjustWeight(Product product, double weight){
        
    }
}
