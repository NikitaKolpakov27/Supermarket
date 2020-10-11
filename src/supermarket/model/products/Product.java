package supermarket.model.products;

import supermarket.model.Types;
import supermarket.service.DiscountService;

import java.time.LocalDate;

public class Product {
    protected double price;
    protected LocalDate shelfDate;
    protected Types type;
    protected String name;
    protected String kind;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShelfDate() {
        return shelfDate;
    }

    public Types getType() {
        return type;
    }

    public boolean hasDiscount(Product p, LocalDate date) {
        return new DiscountService().hasDiscount(p, date);
    }

    public boolean hasExpired(Product p, LocalDate date) {
        return new DiscountService().hasExpired(p, date);
    }

    public String toString() {
        return "Категория: " + this.kind + ". Продукт: " + this.name + ". Срок годности до " + this.shelfDate.toString();
    }

}
