package PageControllers;

import NoteObjects.Objects.TextContainer;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
        notePane.setStyle("-fx-background-color: white");
        pressOnCanvas();
    }

    public void addToNotePane(Node... nodes) {
        notePane.getChildren().addAll(nodes);
    }

    public void removeToNotePane(Node... nodes) {
        notePane.getChildren().removeAll(nodes);
    }

    private void pressOnCanvas() {
        notePane.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                TextContainer textContainer = new TextContainer((int) mouseEvent.getX(), (int) mouseEvent.getY());
                Pane pane = textContainer.getPane();
                addToNotePane(pane);
                //pane.requestFocus();
                //pane.getChildren().get(pane.getChildren().size()-1).requestFocus();
            }
        });
    }
}
