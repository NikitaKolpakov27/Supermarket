package supermarket.model;

public class BuyingResult {

    private double quantity;
    private double weighty_price;
    private double price;
    private boolean success;

    public double getQuantity() {
        return quantity;
    }

    public double getWeighty_price() {
        return weighty_price;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setWeighty_price(double weighty_price) {
        this.weighty_price = weighty_price;
    }

    public BuyingResult(double price, boolean success) {
        this.price = price;
        this.success = success;
    }

    public BuyingResult(double quantity, double weighty_price, boolean success) {
        this.quantity = quantity;
        this.weighty_price = weighty_price;
        this.success = success;
    }

    public BuyingResult(boolean success) {
        this.success = success;
    }
}
