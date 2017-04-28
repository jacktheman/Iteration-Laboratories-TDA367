package controllers.fxmlcontrollers;

import controllers.noteobjectcontrollers.ImageContainerController;
import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.NoteObjectObserverI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import models.notemodel.NoteModel;
import services.FileChooserFactory;
import utilities.NoteStateI;
import utilities.PaintState;
import utilities.WriteState;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable, NoteObjectObserverI {

    @FXML
    private AnchorPane fabNotesWindow;

    @FXML
    private AnchorPane notePane;

    private AnchorPane fille;

    private NoteModel currentNote;

    private NoteStateI noteState;

    private List<NoteObjectControllerI> currentControllers;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentControllers = new ArrayList<>();
        noteState = WriteState.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TagPage.fxml"));
        try {
            fille = loader.load();
            fille.setLayoutX(0);
            fille.setLayoutY(150);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    NoteObjectControllerI controller = noteState.setOnMouseReleased(e);
                    controller.addListener(this);
                    currentControllers.add(controller);
                    notePane.getChildren().add(currentControllers.get(currentControllers.size() - 1).getNode());
                } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                    addImageToNote(e.getX(), e.getY());
                }

                notePane.getChildren().get(notePane.getChildren().size() - 1).requestFocus();
            }

        });

    }

    private void addImageToNote(double x, double y) {
        if (currentNote == null) {
            currentNote = new NoteModel();
        }
        notePane.requestFocus();
        try {
            File image = FileChooserFactory.getImageChooser().showOpenDialog(notePane.getScene().getWindow());
            URI uri = image.toURI();
            URL url = uri.toURL();
            ImageContainerController imageContainerController = new ImageContainerController(url, x, y);
            currentNote.addNoteObjectController(imageContainerController);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        notePane.getChildren().clear();
        notePane.getChildren().addAll(currentNote.getNodes());
        currentNote.getNodes().get(currentNote.getNodes().size() - 1).requestFocus();
    }

    @FXML
    private void pressedFilleButton() {
        if (fabNotesWindow.getChildren().contains(fille))
            fabNotesWindow.getChildren().remove(fille);
        else
            fabNotesWindow.getChildren().add(fille);
    }

    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());

        //TODO send the file URL to ImageContainerController
    }

    @FXML
    private void saveNote() {
        //TODO
    }

    @FXML
    private void changeToWriteState() {
        noteState = WriteState.getInstance();
    }

    @FXML
    private void changeToPaintState() {
        noteState = PaintState.getInstance();
    }

    @Override
    public void fireChange(NoteObjectControllerI subject) {
        currentControllers.remove(subject);
        notePane.getChildren().remove(subject.getNode());
    }
}
