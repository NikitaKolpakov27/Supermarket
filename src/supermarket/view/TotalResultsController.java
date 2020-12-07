package supermarket.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    void initialize() {
        quit.setOnAction(actionEvent -> {
            quit.getScene().getWindow().hide();
        });

        //todo: не передаётся объект!!!
        List<String> newBuys = BuyController.BUY_INSTANCE.getNewBuys();
        StringBuilder result = new StringBuilder();

        for (String s : newBuys) {
            result.append(s).append("; ");
        }

        productList.setText(result.toString());
    }
}
