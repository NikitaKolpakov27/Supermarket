package supermarket.service;

import supermarket.model.Buyer;
import supermarket.model.BuyingResult;
import supermarket.model.products.Product;
import supermarket.model.Types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BuyingService {

    public static BuyingResult buy(Product product, Buyer buyer, LocalDate localDate) {
        double price;

        double virtualAccountBS = buyer.getVirtualAccount();
        double accountBS = buyer.getAccount();

        if (product.getType() == Types.SINGLE_PIECE) {

            if (product.hasDiscount(product, localDate)) {
                price = product.getPrice() / DiscountService.discount(product, localDate);
            } else {
                price = product.getPrice();
            }

            if (virtualAccountBS >= price) {
                virtualAccountBS -= price;
                accountBS += price;

                buyer.setVirtualAccount(virtualAccountBS);
                buyer.setAccount(accountBS);

                return new BuyingResult(price, true);
            } else {
                return new BuyingResult(false);
            }

        } else {
            double weighty_price;
            double quantityBS = 1;

            if (product.hasDiscount(product, localDate)) {
                weighty_price = product.getPrice() / DiscountService.discount(product, localDate);
            } else {
                weighty_price = product.getPrice();
            }

            weighty_price *= quantityBS;

            if (virtualAccountBS >= weighty_price) {
                virtualAccountBS -= weighty_price;
                accountBS += weighty_price;

                buyer.setVirtualAccount(virtualAccountBS);
                buyer.setAccount(accountBS);
                return new BuyingResult(quantityBS, weighty_price, true);

            } else {
                return new BuyingResult(false);
            }

        }
    }

    public static LocalDate dayChange(LocalDate localDate) {
        localDate = localDate.plusDays(1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return localDate;
    }

    public static Buyer getRandomBuyer() {
        List<Buyer> byer_list = new ArrayList<>();

        byer_list.add(new Buyer("Alena", 4500));
        byer_list.add(new Buyer("Max", 2300));
        byer_list.add(new Buyer("Misha", 750));
        byer_list.add(new Buyer("Nikita", 1000));
        byer_list.add(new Buyer("Alice", 1450));
        byer_list.add(new Buyer("Sasha", 8000));
        byer_list.add(new Buyer("Lena", 900));

        return byer_list.get((int) (Math.random() * 6));
    }
}
