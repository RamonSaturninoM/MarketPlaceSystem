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
        if(isOrganic()){
            return pricePerKg * 1.5;
        }
        return pricePerKg;
    }
    
    public boolean isOrganic(){
        return isOrganic;
    }
}
