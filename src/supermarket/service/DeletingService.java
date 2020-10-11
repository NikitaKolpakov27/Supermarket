package supermarket.service;

import supermarket.model.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeletingService {
    public static List<List<Product>> checkShelf_date(List<List<Product>> list, LocalDate localDate) {
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
