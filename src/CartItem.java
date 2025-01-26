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

    public double getTotalPrice() {
        return product.calculatePrice() * quantity;
    }
    
    @Override
    public String toString(){
        return String.format("%s, Weight: %.2f kg, Total Price: $%.2f", 
                product.getName(), quantity, price);
    }    
}
