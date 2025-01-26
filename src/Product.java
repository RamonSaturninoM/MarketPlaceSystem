/**
 *
 * @author ramonsaturnino
 */
public abstract class Product {
    
    protected String name;
    protected double weight;
    protected double pricePerKg;
    
    public Product(String name, double weight, double pricePerKg){
        this.name = name;
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }
    
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
    
    public abstract double calculatePrice();
}
