/**
 *
 * @author ramonsaturnino
 */
public class CartItem {
    private Product product;
    private double quantity;
    private double price;
    
    public CartItem(Product product, double quantity){
        this.product = product;
        this.quantity = quantity;
    };
    
    public double Price(){
        return price;
    }
    
    @Override
    public String toString(){
        return (product+" "+quantity+" "+price);
    }    
}
