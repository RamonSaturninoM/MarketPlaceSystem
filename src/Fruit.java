/**
 * A product representation of fruit.
 * 
 * @author ramonsaturnino
 */
public class Fruit extends Product{
    private String season;
    
    /**
     * Represents a fruit product in the marketplace.
     * 
     * @param name
     * @param weight
     * @param pricePerKg
     * @param season 
     * @author ramonsaturnino
     */
    public Fruit(String name, double weight, double pricePerKg, String season){
        super(name, weight, pricePerKg);
        this.season = season;
    }
    
    /**
     * Calculates price based on season.
     * 
     * @return 
     * @author ramonsaturnino
     */
    @Override
    public double calculatePrice(){
        if(season.equals("summer") || season.equals("spring")){
            return pricePerKg;
        }
        return pricePerKg*1.5;
    }
    
    /**
     * Gets season.
     * 
     * @return 
     * @author ramonsaturnino
     */
    public String getSeason(){
        return season;
    }
}
