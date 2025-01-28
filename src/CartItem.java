/**
 * Class works to create properties of am
 * item in the cart.
 * 
 * @author ramonsaturnino
 */
public class CartItem {
    private Product product;
    private double quantity;
    private double price;
    
    /**
     * Represents a product in the cart.
     * 
     * @param product
     * @param quantity 
     * @author ramonsaturnino
     */
    public CartItem(Product product, double quantity){
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    };
    
    public double getPrice(){
        return price;
    }
    
    public double getQuantity() {
        return quantity;
    }
    
    public Product getProduct() {
        return product;
    }    
    
    /**
     * Calculates total price, by weight and product
     * price, having the updated price per kg based 
     * on season, cut type or if it's organic.
     * 
     * @return 
     * @author ramonsaturnino
     */
    public double getTotalPrice() {
        return product.calculatePrice() * quantity;
    }
    
    /**
     * Formatting output.
     * 
     * @return 
     * @author ramonsaturnino
     */
    @Override
    public String toString(){
        return String.format("%s, Weight: %.2f kg, Total Price: $%.2f", 
                product.getName(), quantity, price);
    }    
}
