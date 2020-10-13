package supermarket.service;

import supermarket.model.products.Alcohol;
import supermarket.model.products.Dairy;
import supermarket.model.products.Product;
import supermarket.model.products.Vegetables;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DiscountService {
    public boolean hasDiscount(Product product, LocalDate date) {
        return product.getShelfDate().until(date, ChronoUnit.DAYS) >= 0
                && product.getShelfDate().until(date, ChronoUnit.DAYS) <= 3;
    }

    public static boolean hasExpired(Product product, LocalDate date) {
        return product.getShelfDate().isBefore(date);
    }

    public static double discount(Product p, LocalDate date) {
        double discount = 0;
        double koef = 1;

        if (p.getShelfDate().until(date, ChronoUnit.DAYS) == 3) {
            koef = 1.05;
        } else if (p.getShelfDate().until(date, ChronoUnit.DAYS) == 2) {
            koef = 1.15;
        } else if (p.getShelfDate().until(date, ChronoUnit.DAYS) == 1) {
            koef = 1.2;
        } else if (p.getShelfDate().until(date, ChronoUnit.DAYS) == 0) {
            koef = 1.25;
        }

        if (p instanceof Dairy) {
            discount = 1.5 * koef;
        } else if (p instanceof Vegetables) {
            discount = 1.2 * koef;
        } else if (p instanceof Alcohol) {
            discount = 1.11;
        } else {
            discount = 1;
        }

        return discount;
    }


}
