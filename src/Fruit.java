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
        if(season.equals("summer") || season.equals("spring")){
            return pricePerKg;
        }
        return pricePerKg*1.5;
    }
    
    public String getSeason(){
        return season;
    }
}
