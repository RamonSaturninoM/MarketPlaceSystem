/**
 *
 * @author ramonsaturnino
 */
public class Vegetable extends Product{
    private boolean isOrganic;
    
    public Vegetable(String name, double weight, double pricePerKg, boolean isOrganic){
        super(name, weight, pricePerKg);
        this.isOrganic = isOrganic;
    }
    
    @Override
    public double calculatePrice(){
        return pricePerKg;
    }
}
