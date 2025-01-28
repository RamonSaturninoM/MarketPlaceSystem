/**
 * Product class, to have a defined structure
 * for each product child.
 * 
 * @author ramonsaturnino
 */
public abstract class Product {
    
    protected String name;
    protected double weight;
    protected double pricePerKg;
    
    /**
     * Represents a product.
     * 
     * @param name
     * @param weight
     * @param pricePerKg 
     * @author ramonsaturnino
     */
    public Product(String name, double weight, double pricePerKg){
        this.name = name;
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }
    
    /**
     * Getters and setters. 
     * @return 
     * @author ramonsaturnino
     */
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return name + ", Weight: " + weight + " kg, Price: $" + pricePerKg + "/kg";
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public double getWeight(){
        return weight;
    }
    
    /**
     * Let the manager update their stock.
     * 
     * @param quantity 
     * @author ramonsaturnino
     */
    public void updateStock(double quantity) {
        if (quantity > weight) {
            System.out.println("Error: Not enough stock available.");
        } else {
            weight -= quantity;  // Reduce the available weight
        }
    }
    
    /**
     * Parent method to implement on child
     * classes for specific products.
     * 
     * @return 
     * @author ramonsaturnino
     */
    public abstract double calculatePrice();
}
