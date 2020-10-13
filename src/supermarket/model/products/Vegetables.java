package supermarket.model.products;

import supermarket.model.Types;

import java.time.LocalDate;

public class Vegetables extends Product {
    public Vegetables(LocalDate date, String name, String kind, double price) {
        super(date, name, kind, price);
        this.type = Types.WEIGHTY;
    }
}
