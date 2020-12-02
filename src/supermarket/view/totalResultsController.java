package supermarket.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class totalResultsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button quit;

    @FXML
    void initialize() {
        quit.setOnAction(actionEvent -> {
            quit.getScene().getWindow().hide();
        });
    }
}
