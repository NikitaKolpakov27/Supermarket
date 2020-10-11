package supermarket.model.products;

import supermarket.model.Types;

import java.time.LocalDate;

public class Dairy extends Product {

    public Dairy(int year, int month, int day, String name, String kind, double price) {
        this.shelfDate = LocalDate.of(year, month, day);
        this.name = name;
        this.price = price;
        this.type = Types.SINGLE_PIECE;
        this.kind = kind;
    }

}
