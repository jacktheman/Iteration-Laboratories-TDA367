package com.itlabs.fabnotes.fxml.controller;

import com.itlabs.fabnotes.fxml.service.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.xml.sax.SAXException;
import com.itlabs.fabnotes.service.StateHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by aron on 2017-03-29.
 */
public class MainPageController implements Initializable {

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
    private FlowPane tagBar;
    @FXML
    private TextField addTagTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ToggleButton newFabNote;

    static final String TAG_PANE_PATH = "/com/itlabs/fabnotes/TagPane.fxml";

    private static final String TAG_PAGE_PATH = "/com/itlabs/fabnotes/TagPage.fxml";

    private static final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private static final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private static final int TRIANGLE_NUMBER_OF_CORNERS = 3;

    private static final double BRUSH_SHRINK_RATE = 0.9;
    private static final double BRUSH_ENLARGE_RATE = 1.1;

    private static final Integer[] DEFAULT_FONT_SIZES = {9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 28, 30};

    private static final int TAG_PAGE_LAYOUT_X = 0;
    private static final int TAG_PAGE_LAYOUT_Y = 150;
    private static final int TAG_PAGE_TRANSLATE_X = -301;
    private static final int TAG_PAGE_TO_X = 0;
    private static final int TAG_PAGE_ANIMATION_DELAY = 650;

    private static final String DEFAULT_FONT = "Calibri";

    private static final String SQUARE = "square";
    private static final String CIRCLE = "circle";
    private static final String TRIANGLE = "triangle";

    private static MainPageController SINGLETON;

    private AnchorPane fille;

    public static MainPageController getInstance() {
        return SINGLETON;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SINGLETON = this;

        setNewFabNote();

        initFontComboBox();
        initTextSizeComboBox();

        loadFileManager();
        prepareSlideMenuAnimation();

        setOnMousePressedNotePane();
        setOnMouseReleasedNotePane();
        setOnNameTextFieldChanged();

        initPaintConfigs();
        setBrushPicture();
    }

    private void initPaintConfigs() {
        colorPicker.setValue(Color.BLACK);
        NoteObjectBridge.setPaintbrush("circle");
        changeColor();
        circleButton.setSelected(true);
    }

    public void loadNoteSave(SavedNoteBridge savedNoteBridge) {
        if (savedNoteBridge.initializeNoteObjects()) {
            loadNoteTagsInTagBar(savedNoteBridge.getTags());
            nameTextField.setText(savedNoteBridge.getName());
        }
    }

    public void loadNoteTagsInTagBar(List<String> tags) {
        tagBar.getChildren().clear();
        if (!tags.isEmpty()) {
            String[] tagsArray = tags.toArray(new String[tags.size()]);
            for (int i = 0; i < tagsArray.length; i++) {
                String tagText = tagsArray[i];
                try {
                    FXMLLoader tagPane = new FXMLLoader(getClass().getResource(TAG_PANE_PATH));
                    AnchorPane tag = tagPane.load();
                    ((Label) tag.getChildren().get(0)).setText(tagText);
                    tagBar.getChildren().add(tag);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initTextSizeComboBox() {
        ObservableList<Integer> sizes = FXCollections.observableArrayList(DEFAULT_FONT_SIZES);
        textSizeComboBox.setItems(sizes);
        textSizeComboBox.getSelectionModel().select(sizes.get(3));
    }

    private void initFontComboBox() {
        ObservableList<String> fonts = FXCollections.observableList(Font.getFamilies());
        textFontComboBox.setItems(fonts);
        textFontComboBox.getSelectionModel().select(DEFAULT_FONT);
    }

    private void loadFileManager() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TAG_PAGE_PATH));
        try {
            fille = loader.load();
            fille.setLayoutX(TAG_PAGE_LAYOUT_X);
            fille.setTranslateX(TAG_PAGE_TRANSLATE_X);
            fille.setLayoutY(TAG_PAGE_LAYOUT_Y);
            fabNotesWindow.getChildren().add(fille);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setOnNameTextFieldChanged() {
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("") || newValue.matches("[A-ZÅÄÖÆØÐÞa-zåäöæøðþ0-9_ ]+"))
                NoteBridge.setNoteName(newValue);
            else
                nameTextField.setText(oldValue);
        });
    }

    private void setOnMousePressedNotePane() {
        notePane.setOnMousePressed(event -> {
            try {
                StateHandler.getInstance().getState().getOnMousePressed(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    private void setOnMouseReleasedNotePane() {
        notePane.setOnMouseReleased(event -> {
            try {
                StateHandler.getInstance().getState().getOnMouseReleased(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    private void prepareSlideMenuAnimation() {
        TranslateTransition openFille = new TranslateTransition(new Duration(TAG_PAGE_ANIMATION_DELAY), fille);
        openFille.setToX(TAG_PAGE_TO_X);
        TranslateTransition closeFille = new TranslateTransition(new Duration(TAG_PAGE_ANIMATION_DELAY), fille);
        tagMenuButton.setOnMouseClicked(mouseEvent -> {
            fille.toFront();
            if (fille.getTranslateX() != TAG_PAGE_TO_X) {
                openFille.play();
            } else {
                closeFille.setToX(TAG_PAGE_TRANSLATE_X);
                closeFille.play();
            }
        });
    }

    //A method for painting the dynamical picture of the paintbrush when changed.
    private void setBrushPicture() {
        //clears picture before painting new
        brushPictureCanvas.getGraphicsContext2D().clearRect(brushPictureCanvas.getLayoutX(), brushPictureCanvas.getLayoutY(),
                brushPictureCanvas.getWidth(), brushPictureCanvas.getHeight());
        //paints new picture on Canvas
        switch (NoteObjectBridge.getPaintbrush()) {
            case TRIANGLE:
                paintTriangle();
                break;
            case SQUARE:
                paintSquare();
                break;
            case CIRCLE:
                paintCircle();
                break;
        }
    }

    private void paintTriangle(){
        brushPictureCanvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        double[] xPoints = {brushPicture.getPrefWidth() / 2 - NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefWidth() / 2, brushPicture.getPrefWidth() / 2
                + NoteObjectBridge.getPaintbrushSize() * TRIANGLE_QUANTIFIER_BIG};
        double[] yPoints = {brushPicture.getPrefHeight() / 2 + NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_SMALL, brushPicture.getPrefHeight() / 2 - NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefHeight() / 2 + NoteObjectBridge.getPaintbrushSize()
                * TRIANGLE_QUANTIFIER_SMALL};
        brushPictureCanvas.getGraphicsContext2D().fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
    }

    private void paintSquare(){
        brushPictureCanvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        brushPictureCanvas.getGraphicsContext2D().fillRect(brushPicture.getPrefWidth() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                brushPicture.getPrefHeight() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                NoteObjectBridge.getPaintbrushSize(), NoteObjectBridge.getPaintbrushSize());
    }

    private void paintCircle(){
        brushPictureCanvas.getGraphicsContext2D().setFill(NoteObjectBridge.getPaintbrushColor());
        brushPictureCanvas.getGraphicsContext2D().fillOval(brushPicture.getPrefWidth() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                brushPicture.getPrefHeight() / 2 - (NoteObjectBridge.getPaintbrushSize() / 2),
                NoteObjectBridge.getPaintbrushSize(), NoteObjectBridge.getPaintbrushSize());
    }

    @FXML
    public void setNewFabNote() {
        NoteBridge.createNewNote(notePane.getChildren());
        nameTextField.setText("");
        tagBar.getChildren().clear();
    }

    @FXML
    private void undoAction() {
        NoteBridge.undoNoteAction();
    }

    @FXML
    private void addTag(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String newTagText = addTagTextField.getText();
            try {
                if (NoteBridge.addNoteTag(newTagText)) {
                    FXMLLoader newTag = new FXMLLoader(getClass().getResource(TAG_PANE_PATH));
                    AnchorPane tag = newTag.load();
                    ((Label) tag.getChildren().get(0)).setText(newTagText);
                    tagBar.getChildren().add(tag);
                    TagsListBridge.addTagToTagsList(newTagText);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            addTagTextField.clear();
            TagPageController.getInstance().getTagFlowPane().getChildren().clear();
            TagPageController.loadTagFlowPane();
        }
    }

    @FXML
    private void makeItalics() {
        if (italicsToggleButton.isSelected()) {
            NoteObjectBridge.setIsItalic(true);
        } else {
            NoteObjectBridge.setIsItalic(false);
        }
    }

    @FXML
    private void makeBold() {
        if (boldToggleButton.isSelected()) {
            NoteObjectBridge.setIsBold(true);
        } else {
            NoteObjectBridge.setIsBold(false);
        }
    }

    @FXML
    private void changeFont() {
        NoteObjectBridge.setFontFamilyName((String) textFontComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void changeSize() {
        NoteObjectBridge.setFontSize((int) textSizeComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());
        try {
            NoteObjectBridge.addImageToNote(image.toURI().toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeColor() {
        NoteObjectBridge.setPaintbrushColor(colorPicker.getValue());
        setBrushPicture();
    }

    @FXML
    private void saveNote() {
        nameTextField.setText(NoteBridge.getNoteName());
        try {
            NoteBridge.saveNote();
            TagPageController.updateNoteList();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadNote() {
        FileChooser fileChooser = FileChooserFactory.getFabNotesChooser();
        File file = fileChooser.showOpenDialog(this.notePane.getScene().getWindow());
        try {
            this.loadNoteSave(SavedNoteBridge.loadSavedNote(file));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeToWriteState() {
        StateHandler.getInstance().changeToWriteState();
    }

    @FXML
    private void changeToPaintState() {
        StateHandler.getInstance().changeToPaintState();
    }

    @FXML
    private void setPaintbrushToSquare() {
        NoteObjectBridge.setPaintbrush(SQUARE);
        circleButton.setSelected(false);
        triangleButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToCircle() {
        NoteObjectBridge.setPaintbrush(CIRCLE);
        squareButton.setSelected(false);
        triangleButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToTriangle() {
        NoteObjectBridge.setPaintbrush(TRIANGLE);
        circleButton.setSelected(false);
        squareButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void shrinkBrushSize() {
        NoteObjectBridge.setPaintbrushSize(
                NoteObjectBridge.getPaintbrushSize() * BRUSH_SHRINK_RATE);
        setBrushPicture();
    }

    @FXML
    private void enlargeBrushSize() {
        NoteObjectBridge.setPaintbrushSize(
                NoteObjectBridge.getPaintbrushSize() * BRUSH_ENLARGE_RATE);
        setBrushPicture();
    }
}
