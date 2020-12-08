package supermarket.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import supermarket.model.Buyer;
import supermarket.service.BuyingService;

public class TotalResultsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label productList;

    @FXML
    private Button quit;

    @FXML
    private Label totalMoney;

    @FXML
    private Label buyer;

    @FXML
    private Label money;

    @FXML
    void initialize() {
        quit.setOnAction(actionEvent -> {
            quit.getScene().getWindow().hide();
        });

        BuyController bc = BuyController.BUY_INSTANCE;
        //todo: нужно написать "вы ничего не купили"


        //List<String> newBuys = BuyController.BUY_INSTANCE.getNewBuys();
        List<String> newBuys = bc.getNewBuys();
        StringBuilder result = new StringBuilder();

        for (String s : newBuys) {
            result.append(s).append("; ");
        }

        productList.setText(result.toString());
        Buyer nikita = BuyingService.INSTANCE.getCurrentBuyer();
        nikita.setMoney(nikita.getMoney() - nikita.getAccount());

        buyer.setText(nikita.getName());
        money.setText(String.valueOf(nikita.getMoney()));
        totalMoney.setText(String.valueOf(nikita.getAccount()));
    }
}
