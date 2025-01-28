/**
 * Class for vegetable object.
 * 
 * @author ramonsaturnino
 */
public class Vegetable extends Product{
    private boolean isOrganic;
    
    /**
     * Represents a vegetable product in marketplace.
     * 
     * @param name
     * @param weight
     * @param pricePerKg
     * @param isOrganic 
     * @author ramonsaturnino
     */
    public Vegetable(String name, double weight, double pricePerKg, boolean isOrganic){
        super(name, weight, pricePerKg);
        this.isOrganic = isOrganic;
    }
    
    /**
     * Returns the price per Kg, depending if 
     * it's organic or not.
     * 
     * @return 
     */
    @Override
    public double calculatePrice(){
        if(isOrganic()){
            return pricePerKg * 1.5;
        }
        return pricePerKg;
    }
    
    /**
     * Checks if the product is organic.
     * 
     * @return 
     * @author ramonsaturnino
     */
    public boolean isOrganic(){
        return isOrganic;
    }
}
