/**
 *
 * @author ramonsaturnino
 */
public class Meat extends Product{
    private String cutType;
    
    public Meat(String name, double weight, double pricePerKg, String cutType){
        super(name, weight, pricePerKg);
        this.cutType = cutType;
    }
    
    @Override
    public double calculatePrice(){
        return pricePerKg;
    }
}
