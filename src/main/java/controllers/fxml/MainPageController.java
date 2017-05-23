package controllers.fxml;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import models.note.Note;
import factory.FileChooserFactory;
import org.xml.sax.SAXException;
import services.FileHandler;
import services.NoteObjectConfigHelper;
import services.StateHandler;
import save.NoteSave;
import utilities.Paintbrush;
import events.Event;

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

    static final String TAG_PANE_PATH = "/TagPane.fxml";

    private static final String TAG_PAGE_PATH = "/TagPage.fxml";

    private static final double TRIANGLE_QUANTIFIER_SMALL = 0.75;
    private static final double TRIANGLE_QUANTIFIER_BIG = 1.25;
    private static final int TRIANGLE_NUMBER_OF_CORNERS = 3;

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

        Note.setCurrentNote(new Note());
        Note.setCurrentNodes(notePane.getChildren());

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
        NoteObjectConfigHelper.setPaintbrush("circle");
        changeColor();
        circleButton.setSelected(true);
    }

    public void loadNoteSave(NoteSave noteSave) {
        if (noteSave.loadControllers()) {
            loadNoteTagsInTagBar(noteSave.getTags());
            nameTextField.setText(noteSave.getName());
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

    public void setNewFabNote() {
        Note.setCurrentNote(new Note());
        Note.getCurrentNodes().clear();
        nameTextField.setText("");
        tagBar.getChildren().clear();
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
                Note.getCurrentNote().setName(newValue);
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

    private void setBrushPicture() {
        GraphicsContext gc = brushPictureCanvas.getGraphicsContext2D();
        gc.clearRect(brushPictureCanvas.getLayoutX(), brushPictureCanvas.getLayoutY(), brushPictureCanvas.getWidth(), brushPictureCanvas.getHeight());
        gc.setFill(Paintbrush.getColor());
        switch (NoteObjectConfigHelper.getPaintbrush()) {
            case "triangle":
                double[] xPoints = {brushPicture.getPrefWidth() / 2 - Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefWidth() / 2, brushPicture.getPrefWidth() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG};
                double[] yPoints = {brushPicture.getPrefHeight() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_SMALL, brushPicture.getPrefHeight() / 2 - Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefHeight() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_SMALL};
                gc.fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
                break;
            case "square":
                gc.fillRect(brushPicture.getPrefWidth() / 2 - (Paintbrush.getSize() / 2), brushPicture.getPrefHeight() / 2 - (Paintbrush.getSize() / 2), Paintbrush.getSize(), Paintbrush.getSize());
                break;
            case "circle":
                gc.fillOval(brushPicture.getPrefWidth() / 2 - (Paintbrush.getSize() / 2), brushPicture.getPrefHeight() / 2 - (Paintbrush.getSize() / 2), Paintbrush.getSize(), Paintbrush.getSize());
                break;
        }

    }

    @FXML
    private void undoAction() {
        Event.getEvents().get(Event.getEvents().size() - 1).undo();
    }

    @FXML
    private void addTag(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            String newTagText = addTagTextField.getText();
            try {
                if (Note.getCurrentNote().addTag(newTagText)) {
                    FXMLLoader newTag = new FXMLLoader(getClass().getResource("/TagPane.fxml"));
                    AnchorPane tag = newTag.load();
                    ((Label) tag.getChildren().get(0)).setText(newTagText);
                    tagBar.getChildren().add(tag);
                    FileHandler.addTags(newTagText);
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
            NoteObjectConfigHelper.setIsItalic(true);
        } else {
            NoteObjectConfigHelper.setIsItalic(false);
        }
    }

    @FXML
    private void makeBold() {
        if (boldToggleButton.isSelected()) {
            NoteObjectConfigHelper.setIsBold(true);
        } else {
            NoteObjectConfigHelper.setIsBold(false);
        }
    }

    @FXML
    private void changeFont() {
        NoteObjectConfigHelper.setFontFamilyName((String) textFontComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void changeSize() {
        NoteObjectConfigHelper.setFontSize((int) textSizeComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());
        try {
            NoteObjectConfigHelper.addImageToNote(image.toURI().toURL());
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
    private void saveNote() {
        Note.getCurrentNote().setName(FileHandler.checkFileName(Note.getCurrentNote().getName()));
        nameTextField.setText(Note.getCurrentNote().getName());
        try {
            FileHandler.saveNote(new NoteSave(Note.getCurrentNote()));
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
            //NoteSave noteSave = FileHandler.loadNote(file);
            NoteSave noteSave = FileHandler.loadNote(file);
            this.loadNoteSave(noteSave);
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
        NoteObjectConfigHelper.setPaintbrush(SQUARE);
        circleButton.setSelected(false);
        triangleButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToCircle() {
        NoteObjectConfigHelper.setPaintbrush(CIRCLE);
        squareButton.setSelected(false);
        triangleButton.setSelected(false);
        setBrushPicture();
    }

    @FXML
    private void setPaintbrushToTriangle() {
        NoteObjectConfigHelper.setPaintbrush(TRIANGLE);
        circleButton.setSelected(false);
        squareButton.setSelected(false);
        setBrushPicture();
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
}
