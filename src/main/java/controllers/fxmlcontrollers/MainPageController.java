package controllers.fxmlcontrollers;

import controllers.noteobjectcontrollers.TextContainerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.notemodel.NoteModel;
import services.FileChooserFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable {

   @FXML
    private AnchorPane notePane;

    private NoteModel currentNote;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        pressedOnCanvas();
    }

    private void pressedOnCanvas() {
        notePane.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY))
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

    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());

        //TODO send the file URL to ImageContainerController
    }

    @FXML
    private void saveNote() {

    }

}
