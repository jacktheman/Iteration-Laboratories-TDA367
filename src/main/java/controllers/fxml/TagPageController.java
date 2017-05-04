package controllers.fxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-31.
 */
public class TagPageController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML //TODO fix so that: ListView<T>
    private ListView noteListView;

    @FXML
    private FlowPane tagFlowPane;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}