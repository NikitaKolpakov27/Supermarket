package supermarket.service.utils;

import supermarket.model.products.Product;

import java.util.HashMap;
import java.util.List;

public class Utils {
    public static HashMap findIndex(List<List<Product>> list, String product) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j).toString().contains(product)) {
                    map.put(i, j);
                    break;
                }
            }
        }
        return map;

    }
}

