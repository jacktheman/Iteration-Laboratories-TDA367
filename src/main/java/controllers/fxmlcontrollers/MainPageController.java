package controllers.fxmlcontrollers;

import controllers.noteobjectcontrollers.ImageContainerController;
import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.TextContainerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.notemodel.NoteModel;
import services.FileChooserFactory;
import utilities.NoteStateI;
import utilities.WriteState;
import views.noteobjectviews.ImageContainerView;
import views.noteobjectviews.TextContainerView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable {

    @FXML
    private AnchorPane notePane;

    private NoteModel currentNote;

    private NoteStateI noteState;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        noteState = WriteState.getInstance();
        pressedOnCanvas();
    }

    private void pressedOnCanvas() {
        notePane.setOnMouseReleased(e -> {
            Node focusOwner = notePane.getScene().getFocusOwner();
            boolean addToNotePane = true;
            if (notePane.getChildren().contains(focusOwner)) {
                double x1 = focusOwner.getLayoutX();
                double x2 = focusOwner.getLayoutX() + focusOwner.getBoundsInLocal().getWidth();
                double y1 = focusOwner.getLayoutY();
                double y2 = focusOwner.getLayoutY() + focusOwner.getBoundsInLocal().getHeight();

                if ((e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2)) {
                    addToNotePane = false;
                }

            }

            if (addToNotePane) {
                if (e.getButton().equals(MouseButton.PRIMARY))
                    addTextToNote(e.getX(), e.getY());
                else if (e.getButton().equals(MouseButton.SECONDARY))
                    addImageToNote(e.getX(), e.getY());
            }
        });

    }

    private void addImageToNote(double x, double y) {
        if (currentNote == null) {
            currentNote = new NoteModel();
        }
        notePane.requestFocus();
        try {
            currentNote.addNoteObjectController(new ImageContainerController(FileChooserFactory.getImageChooser().showOpenDialog(notePane.getScene().getWindow()).toURI().toURL(), x, y));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        notePane.getChildren().clear();
        notePane.getChildren().addAll(currentNote.getNodes());
        currentNote.getNodes().get(currentNote.getNodes().size() - 1).requestFocus();
    }

    private void addTextToNote(double x, double y) {

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
