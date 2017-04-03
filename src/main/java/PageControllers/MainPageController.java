package PageControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable{

    public static MainPageController instance;

    @FXML
    private AnchorPane notePane;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
    }

    public void addToNotePane(Node... nodes) {
        notePane.getChildren().addAll(nodes);
    }

    public void removeToNotePane(Node... nodes) {
        notePane.getChildren().removeAll(nodes);
    }
}
