package supermarket;

import supermarket.model.Buyer;
import supermarket.model.products.Product;
import supermarket.service.BuyingService;
import supermarket.service.ProductService;
import supermarket.view.Show;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int days = 7;
        Show show = new Show();
        LocalDate localDate = LocalDate.now();
        while (days > 0) {
            List<List<Product>> list = new ArrayList<>();
            Buyer nikita = BuyingService.getRandomBuyer();
            show.greetings(localDate, nikita); //Приветствие

            ProductService.addToList(list); //Добавление продуктов

            ProductService.checkShelfDate(list, localDate); //Удалить просрочку

            show.items(list, localDate); // Показ продуктов

            show.buying(nikita, list, localDate); // Процесс покупки

            localDate = BuyingService.dayChange(localDate); // Смена дня
            days--;
        }
    }


}
