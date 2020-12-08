package supermarket.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import supermarket.model.Buyer;
import supermarket.model.Types;
import supermarket.model.products.Product;
import supermarket.service.BuyingService;

public class BuyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label conditionSecond;

    @FXML
    private Label conditionSingle;

    @FXML
    private Spinner<Double> grams;

    @FXML
    private Button buy;

    @FXML
    private Label buyStatus;

    @FXML
    private Spinner<Double> quantity;

    @FXML
    private TextArea product;

    @FXML
    private Label buyMenu;

    @FXML
    private Label youChoosed;

    @FXML
    private ImageView picture;

    @FXML
    private Button kassa;

    @FXML
    private Button elseBuy;

    private List<String> newBuys = new ArrayList<>();

    public static final BuyController BUY_INSTANCE = new BuyController();

    public List<String> getNewBuys() {
        return newBuys;
    }

    public void setNewBuys(List<String> newBuys) {
        this.newBuys = newBuys;
    }

    public Spinner<Double> getGrams() {
        return grams;
    }

    public Spinner<Double> getQuantity() {
        return quantity;
    }

    @FXML
    void initialize() throws FileNotFoundException {
        List<String> buys = new ArrayList<>();
        Stage s = new Stage();
        s.setTitle("Меню покупки");

        Image vodka = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\vodka.jpg"));
        Image apples = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\apples.jpg"));
        Image milk = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\milk.png"));

        Product prod = BuyingService.INSTANCE.getCurrentProduct();
        Buyer buyer = BuyingService.INSTANCE.getCurrentBuyer();
        product.setText(prod.getName());

        if (product.getText().equalsIgnoreCase("Водка")) {
            picture.setImage(vodka);
            conditionSecond.setVisible(false);
            grams.setVisible(false);
        }

        if (product.getText().equalsIgnoreCase("Яблоки")) {
            picture.setImage(apples);
            conditionSingle.setText("Выберете, сколько хотите взять килограммов");
        }

        if (product.getText().equalsIgnoreCase("Молоко")) {
            picture.setImage(milk);
            conditionSecond.setVisible(false);
            grams.setVisible(false);
        }

        elseBuy.setOnAction(actionEvent -> {
            elseBuy.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("guiSample.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        kassa.setOnAction(actionEvent -> {
            kassa.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("totalResults.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        buy.setOnAction(actionEvent -> {
            if (prod.getType() == Types.SINGLE_PIECE) {
                double quan = quantity.getValue();
                double finalPrice = prod.getPrice() * quan;

                if (buyer.getMoney() >= finalPrice) {
                    buyStatus.setText("Куплено!");
                    buyer.setAccount(buyer.getAccount() - finalPrice);
                    buys.add(prod.getName() + "=" + 1);
                } else {
                    buyStatus.setText("Недостаточно денег!");
                }

            } else {
                double kg = quantity.getValue();
                double grms = grams.getValue() * 0.001;
                double wholeProduct = kg + grms;

                double finalPrice = prod.getPrice() * wholeProduct;

                if (buyer.getMoney() >= finalPrice) {
                    buyStatus.setText("Куплено!");
                    buyer.setAccount(buyer.getAccount() - finalPrice);
                    buys.add(prod.getName() + "=" + wholeProduct + "кг.");
                } else {
                    buyStatus.setText("Недостаточно денег!");
                }
            }
            BUY_INSTANCE.setNewBuys(buys);


        });

        SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 50.0);
        SpinnerValueFactory<Double> svf2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 999.0);
        quantity.setValueFactory(svf);
        grams.setValueFactory(svf2);

        grams.setEditable(true);
        quantity.setEditable(true);
    }
}

