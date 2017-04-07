package controllers.fxmlcontrollers;

import controllers.noteobjectcontrollers.TextContainerController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import models.notemodel.NoteModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable{

    public static MainPageController instance;

    @FXML
    private AnchorPane notePane;

    private NoteModel currentNote;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        notePane.setStyle("-fx-background-color: white");
        pressedOnCanvas();
    }

    private void pressedOnCanvas() {
        notePane.setOnMouseReleased(e -> {
            addToNote(e.getX(), e.getY());
        });
    }

    private void addToNote(double x, double y) {
        if (currentNote == null) {
            currentNote = new NoteModel();
        }
        notePane.requestFocus();
        currentNote.addNoteObjectController(new TextContainerController("", x, y));
        notePane.getChildren().clear();
        notePane.getChildren().addAll(currentNote.getNodes());
        currentNote.getNodes().get(currentNote.getNodes().size() - 1).requestFocus();
    }


}
