package supermarket.service;

import supermarket.model.products.Alcohol;
import supermarket.model.products.Dairy;
import supermarket.model.products.Product;
import supermarket.model.products.Vegetables;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductService {
    public static List<List<Product>> addToList(List<List<Product>> list) {
        List<Product> veg = new ArrayList<>();
        List<Product> dairies = new ArrayList<>();
        List<Product> alc = new ArrayList<>();

        LocalDate milkDate = LocalDate.of(2020, 10, 24);
        LocalDate tomatDate = LocalDate.of(2020, 10, 12);
        LocalDate applesDate = LocalDate.of(2020, 12, 11);
        LocalDate vodkaDate = LocalDate.of(2023, 10, 24);


        Product milk = new Dairy(milkDate, "Молоко", "Молочная продукция", 64.50);
        Product tomat = new Vegetables(tomatDate,"Томаты", "Овощи и фрукты", 33.99);
        Product apples = new Vegetables(applesDate,"Яблоки", "Овощи и фрукты", 78.29);
        Product vodka = new Alcohol(vodkaDate,"Водка", "Алкоголь", 2499.99);

        alc.add(vodka);
        dairies.add(milk);
        veg.add(tomat);
        veg.add(apples);

        list.add(dairies);
        list.add(veg);
        list.add(alc);

        return list;
    }

    public static List<List<Product>> checkShelfDate(List<List<Product>> list, LocalDate localDate) {
        List<List<Product>> copy = new ArrayList<>(list);
        Collections.copy(copy, list);

        for (int i = 0; i < copy.size(); i++) {
            for (int j = 0; j < copy.get(i).size(); j++) {
                Product p = copy.get(i).get(j);
                list.get(j).removeIf(product -> DiscountService.hasExpired(product, localDate));
            }
        }
        return list;

    }
}
