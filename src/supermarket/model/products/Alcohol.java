package supermarket.model.products;

import supermarket.model.Types;

import java.time.LocalDate;

public class Alcohol extends Product {
    public Alcohol(LocalDate date, String name, String kind, double price) {
        super(date, name, kind, price);
        this.type = Types.SINGLE_PIECE;
    }
}

