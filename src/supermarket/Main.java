package supermarket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import supermarket.model.products.Alcohol;
import supermarket.service.utils.Utils;
import supermarket.view.*;
import supermarket.model.Buyer;
import supermarket.model.products.Product;
import supermarket.service.BuyingService;
import supermarket.service.ProductService;
import supermarket.view.Show;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
//        int days = 7;
//        Show show = new Show();
//        LocalDate localDate = LocalDate.now();
//
//        List<List<Product>> list = new ArrayList<>();
//        ProductService.addToList(list);
//        HashMap<Integer, Integer> map = Utils.findIndex(list, "Водка");
//
//        int a = 0;
//        int b = 0;
//
//        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
//            a = pair.getKey();
//            b = pair.getValue();
//            System.out.println(pair.getKey() + " " + pair.getValue());
//        }
//
//        System.out.println(list.get(a).get(b));


        launch(args);

//        while (days > 0) {
//            List<List<Product>> list = new ArrayList<>();
//            Buyer nikita = BuyingService.getRandomBuyer();
//            show.greetings(localDate, nikita); //Приветствие
//
//            ProductService.addToList(list); //Добавление продуктов
//
//            ProductService.checkShelfDate(list, localDate); //Удалить просрочку
//
//            show.items(list, localDate); // Показ продуктов
//
//            show.buying(nikita, list, localDate); // Процесс покупки
//
//            localDate = Show.dayChange(localDate); // Смена дня
//            days--;
//        }
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/guiSample.fxml"));
        primaryStage.setTitle("Супермаркет");
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }


}
