package supermarket.view;

import supermarket.model.Buyer;
import supermarket.model.products.Product;
import supermarket.model.Types;
import supermarket.service.BuyingService;
import supermarket.service.DiscountService;
import supermarket.service.ReturnResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Show {
    public void greetings(LocalDate localDate, Buyer nikita) {
        System.out.println("ДОБРО ПОЖАЛОВАТЬ В СУПЕРМАРКЕТ \"Шестёрочка\"!");
        System.out.println("День: " + localDate);
        System.out.println("Покупатель: " + nikita.getName() + "; На счету: " + nikita.getMoney());
        System.out.println("-----------------------------------------------");
        System.out.print("Наши товары: " +
                "------> ");
    }

    public void items(List<List<Product>> productList, LocalDate localDate) {
        String sale = "";
        for (List<Product> products : productList) {
            for (Product product : products) {
                if (product.hasDiscount(product, localDate)) {
                    int dis = (int) (100 - (100 / DiscountService.discount(product, localDate)));
                    sale = " (скидка " + dis + "%)";
                    System.out.print(String.format("%s - %f руб. %s; ", product.getName(), product.getPrice(), sale));
                } else {
                    System.out.print(product.getName() + " - " + product.getPrice() + " руб.; ");
                }

            }
        }
    }

    public void buying(Buyer buyer, List<List<Product>> list, LocalDate localDate) {
        Scanner scn = new Scanner(System.in);
        String answer = "";
        List<String> newBuys = new ArrayList<>();

        do {
            System.out.println("\nЧто вы хотите взять? --> ");
            double acc = buyer.getAccount();
            answer = scn.nextLine();

            for (List<Product> products : list) {
                for (Product product : products) {
                    if (answer.equalsIgnoreCase(product.getName())) {

                        ReturnResult ret = BuyingService.buy(product, buyer, localDate);
                        //BuyingService.buy(product, buyer, localDate);

                        if (product.getType() == Types.SINGLE_PIECE) {
                            if (ret.isHorosho()) {
                                System.out.println("Вы взяли " + product.getName() + ". Этот продукт стоит: " + ret.getPrice());
                            } else {
                                System.out.println("Недостаточно средств!");
                            }
                        } else {
                            System.out.println("Вы выбрали " + product.getName() + ". Сколько килограммов вы хотите взять?");
                            Scanner scan = new Scanner(System.in);
                            double kg = Double.parseDouble(scan.nextLine());
                            ret.setQuantity(kg);
                            ret.setWeighty_price(ret.getQuantity() * ret.getWeighty_price());

                            if (ret.isHorosho()) {
                                System.out.println(String.format("Вы взяли %s. (%f кг.) Этот товар стоит: %f",
                                        product.getName(), ret.getQuantity(), ret.getWeighty_price()));


//                                        "Вы взяли " + product.getName() + "." + "(" + ret.getQuantity() + " кг.) "
//                                        + "Этот товар стоит: " + ret.getWeighty_price());
                            } else {
                                System.out.println("Недостаточно средств!");
                            }
                        }

                        acc = acc + product.getPrice();



                        if (!(acc > buyer.getMoney())) {
                            if (product.getType() == Types.WEIGHTY) {
                                newBuys.add(product.getName() + " = " + buyer.getQuantity());
                            } else {
                                newBuys.add(product.getName() + " = " + 1);
                            }
                        }

                    }

                }

            }
        } while (!answer.equalsIgnoreCase("ничего"));

        if (newBuys.isEmpty()) {
            System.out.println("Вы ничего не купили. До скорых встреч!\n");
            return;
        }

        System.out.println("Список ваших покупок: --->");
        for (String s : newBuys) {
            System.out.print(s + "; ");
        }

        buyer.setMoney(buyer.getMoney() - buyer.getAccount());
        System.out.println("\n---------------------");
        System.out.println(String.format("Итого к оплате: %d. Денег осталось: %d", (int) buyer.getAccount(), (int) buyer.getMoney()));
        System.out.println("До скорых встреч!\n");
    }
}
