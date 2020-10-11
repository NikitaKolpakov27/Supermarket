package supermarket.service;

import supermarket.model.products.Alcohol;
import supermarket.model.products.Dairy;
import supermarket.model.products.Product;
import supermarket.model.products.Vegetables;

import java.util.ArrayList;
import java.util.List;

public class AddingService {
    public static List<List<Product>> addToList(List<List<Product>> list) {
        List<Product> veg = new ArrayList<>();
        List<Product> dairies = new ArrayList<>();
        List<Product> alc = new ArrayList<>();

        Product milk = new Dairy(2020, 9, 28, "Молоко", "Молочная продукция", 64.50);
        Product tomat = new Vegetables(2020, 10, 15, "Томаты", "Овощи и фрукты", 33.99);
        Product apples = new Vegetables(2020, 9, 18, "Яблоки", "Овощи и фрукты", 78.29);
        Product vodka = new Alcohol(2023, 3, 6, "Водка", "Алкоголь", 2499.99);

        alc.add(vodka);
        dairies.add(milk);
        veg.add(tomat);
        veg.add(apples);

        list.add(dairies);
        list.add(veg);
        list.add(alc);

        return list;
    }
}
