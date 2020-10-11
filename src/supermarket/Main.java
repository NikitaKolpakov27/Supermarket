package supermarket;

import supermarket.model.Buyer;
import supermarket.model.Supermarket;
import supermarket.model.products.Product;
import supermarket.service.AddingService;
import supermarket.service.BuyingService;
import supermarket.service.DeletingService;
import supermarket.view.Show;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalUnit;
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

            AddingService.addToList(list); //Добавление продуктов

            DeletingService.checkShelf_date(list, localDate); //Удалить просрочку

            show.items(list, localDate); // Показ продуктов

            show.buying(nikita, list, localDate); // Процесс покупки

            localDate = BuyingService.dayChange(localDate); // Смена дня
            days--;
        }
    }


}
