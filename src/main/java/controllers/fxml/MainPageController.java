package controllers.fxml;

import controllers.noteobject.NoteObjectControllerI;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import models.note.Note;
import models.noteobject.PaintingContainer;
import services.FileChooserFactory;
import services.ObserverBus;
import services.StateHandler;
import utilities.ObserverI;
import utilities.Paintbrush;
import utilities.state.PaintState;
import utilities.state.WriteState;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static utilities.Paintbrush.CIRCLE;
import static utilities.Paintbrush.SQUARE;
import static utilities.Paintbrush.TRIANGLE;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable, ObserverI<Node> {

    @FXML
    private AnchorPane fabNotesWindow;

    @FXML
    private AnchorPane notePane;

    @FXML
    private Button tagMenuButton;

    @FXML
    private ToggleButton circleButton;

    @FXML
    private ToggleButton squareButton;

    @FXML
    private ToggleButton triangleButton;

    @FXML
    private ColorPicker colorPicker;

    private AnchorPane fille;

    private static Note currentNote;

    @FXML
    private ComboBox textFontComboBox;

    private List<String> fonts = Font.getFamilies();

    @FXML
    private ComboBox textSizeComboBox;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentNote = new Note();
        ObserverBus.addListener(currentNote, this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TagPage.fxml"));
        try {
            fille = loader.load();
            fille.setLayoutX(0);
            fille.setTranslateX(-301);
            fille.setLayoutY(150);
            fabNotesWindow.getChildren().add(fille);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObservableList<String> observableList = FXCollections.observableList(fonts);

        textFontComboBox.setItems(observableList);
        textFontComboBox.getSelectionModel().select("Calibri");
        WriteState.getInstance().setFont((String) textFontComboBox.getSelectionModel().getSelectedItem());

        prepareSlideMenuAnimation();
        setOnMousePressedNotePane();
        setOnMouseReleasedNotePane();
        colorPicker.setValue(Color.BLACK);
    }

    private void setOnMousePressedNotePane() {
        notePane.setOnMousePressed(event -> {
            NoteObjectControllerI controller = null;
            try {
                controller = StateHandler.getInstance().getState().getOnMousePressed(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void changeFont() {
        WriteState.getInstance().setFont((String) textFontComboBox.getSelectionModel().getSelectedItem());
    }

    private void setOnMouseReleasedNotePane() {
        notePane.setOnMouseReleased(event -> {
            NoteObjectControllerI controller = null;
            try {
                controller = StateHandler.getInstance().getState().getOnMouseReleased(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            addNodeToNotePane(controller);
        });
    }


    private void addNodeToNotePane(NoteObjectControllerI controller) {
        if (controller != null) {
            notePane.getChildren().add(controller.getNode());
            notePane.getChildren().get(notePane.getChildren().size() - 1).requestFocus();
        }
    }

    @FXML
    private void pressedFilleButton() {

    } // Empty method but causes problem when removed

    private void prepareSlideMenuAnimation() {
        TranslateTransition openFille = new TranslateTransition(new Duration(1000), fille);
        openFille.setToX(0);
        TranslateTransition closeFille = new TranslateTransition(new Duration(1000), fille);
        tagMenuButton.setOnMouseClicked(mouseEvent -> {
            fille.toFront();
            if (fille.getTranslateX() != 0) {
                openFille.play();
            } else {
                closeFille.setToX(-301);
                closeFille.play();
            }
        });
    }


    public static Note getCurrentNote() {
        return currentNote;
    }


    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());

        //TODO send the file URL to ImageContainerController
    }

    @FXML
    private void changeColor() {
        Paintbrush.setColor(colorPicker.getValue());
    }

    @FXML
    private void saveNote() { //TODO
    }

    @FXML
    private void changeToWriteState() {
        StateHandler.getInstance().setState(WriteState.getInstance());
    }

    @FXML
    private void changeToPaintState() {
        StateHandler.getInstance().setState(PaintState.getInstance());
    }

    @FXML
    private void setPaintbrushToSquare() {
        PaintingContainer.setPaintbrush(SQUARE);
        circleButton.setSelected(false);
        triangleButton.setSelected(false);
    }

    @FXML
    private void setPaintbrushToCircle() {
        PaintingContainer.setPaintbrush(CIRCLE);
        squareButton.setSelected(false);
        triangleButton.setSelected(false);
    }

    @FXML
    private void setPaintbrushToTriangle() {
        PaintingContainer.setPaintbrush(TRIANGLE);
        circleButton.setSelected(false);
        squareButton.setSelected(false);
    }

    @FXML
    private void shrinkBrushSize() {
        Paintbrush.setSize(Paintbrush.getSize() * 0.9);
    }

    @FXML
    private void enlargeBrushSize() {
        Paintbrush.setSize(Paintbrush.getSize() * 1.1);
    }

    @Override
    public void fireChange(Node subject) {
        if (notePane.getChildren().contains(subject))
            notePane.getChildren().remove(subject);
        else
            notePane.getChildren().add(subject);
    }


}
