package supermarket.service;

import supermarket.model.Buyer;
import supermarket.model.products.Product;
import supermarket.model.Types;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyingService {

    public static ReturnResult buy(Product product, Buyer buyer, LocalDate localDate) {
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

                return new ReturnResult(price, true);

                //System.out.println("Вы взяли " + product.getName() + ". Этот продукт стоит: " + price);
            } else {
                //System.out.println("Недостаточно средств!");
                return new ReturnResult(false);
            }

        } else {
            double weighty_price;
            double quantityBS = 1;

            if (product.hasDiscount(product, localDate)) {
                weighty_price = product.getPrice() / DiscountService.discount(product, localDate);
            } else {
                weighty_price = product.getPrice();
            }

            //System.out.println("Вы выбрали " + product.getName() + ". Сколько килограммов вы хотите взять?");
            //Scanner scn = new Scanner(System.in);
            //quantityBS = Double.parseDouble(scn.nextLine());

            weighty_price *= quantityBS;

            if (virtualAccountBS >= weighty_price) {
                virtualAccountBS -= weighty_price;
                accountBS += weighty_price;

                buyer.setVirtualAccount(virtualAccountBS);
                buyer.setAccount(accountBS);

                return new ReturnResult(quantityBS, weighty_price, true);

                //System.out.println("Вы взяли " + product.getName() + "." + "(" + quantityBS + " кг.) " + "Этот товар стоит: " + weighty_price);
            } else {
                //System.out.println("Недостаточно средств!");
                return new ReturnResult(false);
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
