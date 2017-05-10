package controllers.fxml;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    @FXML
    private AnchorPane brushPicture;
    @FXML
    private Canvas brushPictureCanvas;
    @FXML
    private ComboBox textFontComboBox;
    @FXML
    private ComboBox textSizeComboBox;
    @FXML
    private ToggleButton boldToggleButton;
    @FXML
    private ToggleButton italicsToggleButton;
    @FXML
    private ToggleButton underlineToggleButton;
    @FXML
    private HBox tagBar; //behöver egentligen inte göra något med den. den ska bara finnas
    @FXML
    private TextField addTagTextField;

    private final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private final int TRIANGLE_NUMBER_OF_CORNERS = 3;
    private AnchorPane fille;
    private static Note currentNote;
    private List<String> fonts = Font.getFamilies();
    private ObservableList<String> tags;
    private FXMLLoader loaderTag;


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

        ObservableList<String> fonts = FXCollections.observableList(this.fonts);

        textFontComboBox.setItems(fonts);
        textFontComboBox.getSelectionModel().select("Calibri");
        WriteState.getInstance().setFont((String) textFontComboBox.getSelectionModel().getSelectedItem());

        ObservableList<Integer> sizes = FXCollections.observableArrayList(9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 28, 30);
        textSizeComboBox.setItems(sizes);
        textSizeComboBox.getSelectionModel().select(sizes.get(3));

        prepareSlideMenuAnimation();
        setOnMousePressedNotePane();
        setOnMouseReleasedNotePane();
        colorPicker.setValue(Color.BLACK);
        PaintingContainer.setPaintbrush(CIRCLE);
        circleButton.setSelected(true);
        setBrushPicture();

        loaderTag = new FXMLLoader(getClass().getResource("/TagPane.fxml"));

    }

    @FXML
    private void addTag(KeyEvent event) {
        //System.out.println("addTag");
        if (event.getCode().equals(KeyCode.ENTER)) {
            String newTagText = addTagTextField.getText();
            try {
                AnchorPane tag = loaderTag.load();
                ((Label) tag.getChildren().get(0)).setText(newTagText);
                tagBar.getChildren().add(tag);
            } catch (IOException e) {
                e.printStackTrace();
            }
            tags.add(addTagTextField.getText());
            addTagTextField.clear();
        }
    }


    private void setOnMousePressedNotePane() {
        notePane.setOnMousePressed(event -> {
            NoteObjectControllerI controller = null;
            try {
                controller = StateHandler.getInstance().getState().getOnMousePressed(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            addNodeToNotePane(controller);
        });
    }

    @FXML
    private void makeItalics() {
        if (italicsToggleButton.isSelected()) {
            WriteState.getInstance().setFontPosture(FontPosture.ITALIC);
        } else {
            WriteState.getInstance().setFontPosture(FontPosture.REGULAR);
        }
    }

    @FXML
    private void makeBold () {
        if (boldToggleButton.isSelected()) {
            WriteState.getInstance().setFontWeight(FontWeight.BOLD);
        } else {
            WriteState.getInstance().setFontWeight(FontWeight.NORMAL);
        }
    }

    @FXML
    private void changeFont() {
        WriteState.getInstance().setFont((String)textFontComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void changeSize() {
        WriteState.getInstance().setSize((int)textSizeComboBox.getSelectionModel().getSelectedItem());
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
        try {
            currentNote.addNoteObject(new ImageContainerController(image.toURI().toURL(), 0, 0).getNode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeColor() {
        Paintbrush.setColor(colorPicker.getValue());
        setBrushPicture();
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
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToCircle() {
        PaintingContainer.setPaintbrush(CIRCLE);
        squareButton.setSelected(false);
        triangleButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToTriangle() {
        PaintingContainer.setPaintbrush(TRIANGLE);
        circleButton.setSelected(false);
        squareButton.setSelected(false);
        setBrushPicture();
    }

    private void setBrushPicture(){
        GraphicsContext gc = brushPictureCanvas.getGraphicsContext2D();
        gc.clearRect(brushPictureCanvas.getLayoutX(),brushPictureCanvas.getLayoutY(),brushPictureCanvas.getWidth(),brushPictureCanvas.getHeight());
        //gc.setFill(Color.WHITE);
        //gc.fillRect(brushPictureCanvas.getLayoutX(),brushPictureCanvas.getLayoutY(),brushPictureCanvas.getWidth(),brushPictureCanvas.getHeight());
        gc.setFill(Paintbrush.getColor());
        switch (PaintingContainer.getPaintbrush()){
            case TRIANGLE:
                double [] xPoints = {brushPicture.getPrefWidth()/2-Paintbrush.getSize()*TRIANGLE_QUANTIFIER_BIG,brushPicture.getPrefWidth()/2,brushPicture.getPrefWidth()/2+Paintbrush.getSize()*TRIANGLE_QUANTIFIER_BIG};
                double [] yPoints = {brushPicture.getPrefHeight()/2+Paintbrush.getSize()*TRIANGLE_QUANTIFIER_SMALL,brushPicture.getPrefHeight()/2-Paintbrush.getSize()*TRIANGLE_QUANTIFIER_BIG,brushPicture.getPrefHeight()/2+Paintbrush.getSize()*TRIANGLE_QUANTIFIER_SMALL};
                gc.fillPolygon(xPoints,yPoints,TRIANGLE_NUMBER_OF_CORNERS);
                break;
            case SQUARE:
                gc.fillRect(brushPicture.getPrefWidth()/2-(Paintbrush.getSize()/2),brushPicture.getPrefHeight()/2-(Paintbrush.getSize()/2),Paintbrush.getSize(),Paintbrush.getSize());
                break;
            case CIRCLE:
                gc.fillOval(brushPicture.getPrefWidth()/2-(Paintbrush.getSize()/2),brushPicture.getPrefHeight()/2-(Paintbrush.getSize()/2),Paintbrush.getSize(),Paintbrush.getSize());
                break;
        }

    }

    @FXML
    private void shrinkBrushSize() {
        Paintbrush.setSize(Paintbrush.getSize() * 0.9);
        setBrushPicture();
    }

    @FXML
    private void enlargeBrushSize() {
        Paintbrush.setSize(Paintbrush.getSize() * 1.1);
        setBrushPicture();
    }

    @Override
    public void fireChange(Node subject) {
        if (notePane.getChildren().contains(subject))
            notePane.getChildren().remove(subject);
        else
            notePane.getChildren().add(subject);
    }


}
