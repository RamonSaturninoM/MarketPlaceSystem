/**
 * A product representation of meat.
 * 
 * @author ramonsaturnino
 */
public class Meat extends Product{
    private String cutType;
    
    /**
     * Represents a meat product in the marketplace.
     * 
     * @param name
     * @param weight
     * @param pricePerKg
     * @param cutType 
     * @author ramonsaturnino
     */
    public Meat(String name, double weight, double pricePerKg, String cutType){
        super(name, weight, pricePerKg);
        this.cutType = cutType;
    }
    
    /**
     * Calculates price based on weight.
     * 
     * @return 
     * @author ramonsaturnino
     */
    @Override
    public double calculatePrice(){
        return pricePerKg;
    }
    
    /**
     * Get cut type.
     * 
     * @return 
     * @author ramonsaturnino
     */
    public String getCutType(){
        return cutType;
    }
}
