package supermarket.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import supermarket.Main;
import supermarket.model.Buyer;
import supermarket.model.products.Alcohol;
import supermarket.model.products.Product;
import supermarket.service.BuyingService;
import supermarket.service.DiscountService;
import supermarket.service.ProductService;
import supermarket.service.utils.Utils;

public class GUI extends Application {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView vodkaPic;

    @FXML
    private ImageView milkPic;

    @FXML
    private ImageView applesPic;

    @FXML
    private Label pokypatel;

    @FXML
    private Button buyVodka;

    @FXML
    private Button buyMilk;

    @FXML
    private Button buyApples;

    @FXML
    private Label buyerPanel;

    @FXML
    private Label moneyPanel;

    @FXML
    private Label dayPanel;

    @FXML
    private Label vodkaProd;

    @FXML
    private Label milkProd;

    @FXML
    private Label applesProd;

    @FXML
    private Label vodkaPrice;

    @FXML
    private Label milkPrice;

    @FXML
    private Label applesPrice;

    private Product product;

    public Product getProduct() {
        return product;
    }
    Buyer nikita = BuyingService.INSTANCE.getCurrentBuyer();


    @FXML
    void initialize() {
        List<List<Product>> list = new ArrayList<>();
        ProductService.addToList(list);
        LocalDate localDate = LocalDate.now();


        HashMap<Integer, Integer> map = Utils.findIndex(list, "Водка");
        HashMap<Integer, Integer> map2 = Utils.findIndex(list, "Яблоки");
        HashMap<Integer, Integer> map3 = Utils.findIndex(list, "Молоко");

        int a = 0;
        int b = 0;

        int a2 = 0;
        int b2 = 0;

        int a3 = 0;
        int b3 = 0;

        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            a = pair.getKey();
            b = pair.getValue();
        }

        for (Map.Entry<Integer, Integer> pair : map2.entrySet()) {
            a2 = pair.getKey();
            b2 = pair.getValue();
        }

        for (Map.Entry<Integer, Integer> pair : map3.entrySet()) {
            a3 = pair.getKey();
            b3 = pair.getValue();
        }

        Product vodka, apples, milk;
        vodka = list.get(a).get(b);
        apples = list.get(a2).get(b2);
        milk = list.get(a3).get(b3);
        ProductService.checkShelfDate(list, localDate);


        deleteIf(vodka, buyVodka, vodkaPic, localDate, vodkaProd, vodkaPrice);
        deleteIf(milk, buyMilk, milkPic, localDate, milkProd, milkPrice);
        deleteIf(apples, buyApples, applesPic, localDate, applesProd, applesPrice);

        vodkaPrice.setText("Цена: " + vodka.getPrice());
        milkPrice.setText("Цена: " + milk.getPrice());
        applesPrice.setText("Цена: " + apples.getPrice());

        discount(vodka, vodkaPrice, localDate);
        discount(milk, milkPrice, localDate);
        discount(apples, applesPrice, localDate);

        buyVodka.setOnAction(actionEvent -> {
            BuyingService.INSTANCE.setCurrentProduct(vodka);

            if (nikita.getMoney() < vodka.getPrice()) {
                checkMoney(buyVodka);
                return;
            }

            buyVodka.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("buy.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            product = vodka;
        });

        buyApples.setOnAction(actionEvent -> {
            BuyingService.INSTANCE.setCurrentProduct(apples);

            if (nikita.getMoney() < apples.getPrice()) {
                checkMoney(buyApples);
                return;
            }

            if (apples.hasExpired(apples, localDate)) {
                buyApples.setVisible(false);
            }

            buyApples.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("buy.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            product = apples;
        });

        buyMilk.setOnAction(actionEvent -> {
            BuyingService.INSTANCE.setCurrentProduct(milk);

            if (nikita.getMoney() < milk.getPrice()) {
                checkMoney(buyMilk);
                return;
            }

            if (milk.hasExpired(milk, localDate)) {
                buyApples.setVisible(false);
            }

            buyMilk.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("buy.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            product = milk;
        });

        dayPanel.setText(localDate.toString());

        buyerPanel.setText(nikita.getName());
        Double money = nikita.getMoney();
        String balance = money.toString();
        moneyPanel.setText(balance + " руб.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Супермаркет");
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);

        Pane root = new Pane();
        Button but = new Button();
        but.setText("Купить");
        but.setTranslateX(140);
        but.setTranslateY(140);
        but.setPrefSize(70, 40);
        Rectangle rectangle = new Rectangle(1, 0, 100, 50);

        but.setOnAction(event -> rectangle.setFill(Color.BLUE));

        Scene scene = new Scene(root);

        root.getChildren().addAll(but, rectangle);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void checkMoney(Button button) {
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("moneyError.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        return;
    }

    private void deleteIf(Product product, Button button, ImageView image, LocalDate localDate, Label prod, Label price) {
        if (product.hasExpired(product, localDate)) {
            button.setVisible(false);
            image.setVisible(false);
            prod.setVisible(false);
            price.setVisible(false);
        }
    }

    private void discount(Product product, Label price, LocalDate localDate) {
        if (product.hasDiscount(product, localDate)) {
            price.setText("Цена: " + product.getPrice() + "; Цена со скидкой: " + DiscountService.discountCalc(product, localDate));
        }
    }

}


