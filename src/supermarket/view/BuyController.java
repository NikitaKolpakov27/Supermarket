package supermarket.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import supermarket.model.products.Product;

public class BuyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<?> quantity;

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

    @FXML
    void initialize() throws FileNotFoundException {
        Stage s = new Stage();
        s.setTitle("Меню покупки");

        Image vodka = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\vodka.jpg"));
        Image apples = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\apples.jpg"));
        Image milk = new Image(new FileInputStream("C:\\Users\\HP\\IdeaProjects\\supermarket\\src\\supermarket\\view\\assets\\milk.png"));

        Product prod = new GUI().getProduct();
        product.setText("Яблоки");

        if (product.getText().equalsIgnoreCase("Водка")) {
            picture.setImage(vodka);
        }

        if (product.getText().equalsIgnoreCase("Яблоки")) {
            picture.setImage(apples);
        }

        if (product.getText().equalsIgnoreCase("Молоко")) {
            picture.setImage(milk);
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
            stage.showAndWait();
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
            stage.showAndWait();
        });




    }
}

