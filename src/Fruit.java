/**
 *
 * @author ramonsaturnino
 */
public class Fruit extends Product{
    private String season;
    
    public Fruit(String name, double weight, double pricePerKg, String season){
        super(name, weight, pricePerKg);
        this.season = season;
    }
    
    @Override
    public double calculatePrice(){
        return pricePerKg;
    }
}
