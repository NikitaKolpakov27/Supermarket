package supermarket.model.products;

import supermarket.model.Types;

import java.time.LocalDate;

public class Vegetables extends Product {

//    public Vegetables(int year, int month, int day, String name, String kind, double price) {
//        this.shelfDate = LocalDate.of(year, month, day);
//        this.name = name;
//        this.price = price;
//        this.type = Types.WEIGHTY;
//        this.kind = kind;
//    }

    public Vegetables(LocalDate date, String name, String kind, double price) {
        super(date, name, kind, price);
        this.type = Types.WEIGHTY;
    }
}
