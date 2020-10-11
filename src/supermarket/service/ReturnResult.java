package supermarket.service;

public class ReturnResult {
    private double quantity;
    private double weighty_price;
    private double price;
    private boolean horosho;


    public double getQuantity() {
        return quantity;
    }

    public double getWeighty_price() {
        return weighty_price;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHorosho() {
        return horosho;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setWeighty_price(double weighty_price) {
        this.weighty_price = weighty_price;
    }

    public ReturnResult(double price, boolean horosho) {
        this.price = price;
        this.horosho = horosho;
    }

    public ReturnResult(double quantity, double weighty_price, boolean horosho) {
        this.quantity = quantity;
        this.weighty_price = weighty_price;
        this.horosho = horosho;
    }

    public ReturnResult(boolean horosho) {
        this.horosho = horosho;
    }
}
