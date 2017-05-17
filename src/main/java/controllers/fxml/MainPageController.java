package controllers.fxml;

import controllers.noteobject.ImageContainerController;
import controllers.noteobject.NoteObjectControllerI;
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
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import models.note.Note;
import models.noteobject.PaintingContainer;
import models.noteobject.TextContainer;
import services.FileChooserFactory;
import services.FileHandler;
import services.StateHandler;
import services.NoteSave;
import utilities.Paintbrush;
import events.Event;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static utilities.Paintbrush.CIRCLE;
import static utilities.Paintbrush.SQUARE;
import static utilities.Paintbrush.TRIANGLE;

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
    private ToggleButton underlineToggleButton;
    @FXML
    private TilePane tagBar;
    @FXML
    private TextField addTagTextField;
    @FXML
    private TextField nameTextField;

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

    private static MainPageController SINGLETON;

    private AnchorPane fille;

    public static MainPageController getInstance () {
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
        PaintingContainer.setPaintbrush(CIRCLE);
        circleButton.setSelected(true);
    }

    public void loadNoteSave(NoteSave noteSave) {
        List<NoteObjectControllerI> controllers = noteSave.loadControllers();
        Note.getCurrentNodes().clear();
        Note note = new Note(noteSave.getName());
        note.setTags(noteSave.getTags());
        loadNoteTagsInTagBar(noteSave.getTags());
        for (NoteObjectControllerI controller : controllers)
            note.getModels().add(controller.getModel());
        Note.setCurrentNote(note);
        nameTextField.setText(note.getName());
    }

    public void loadNoteTagsInTagBar (String tags) {
        String[] tagsArray = tags.split(Pattern.quote("."));
        System.out.println(tagsArray.toString());
        tagBar.getChildren().clear();
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

    private void initTextSizeComboBox() {
        ObservableList<Integer> sizes = FXCollections.observableArrayList(DEFAULT_FONT_SIZES);
        textSizeComboBox.setItems(sizes);
        textSizeComboBox.getSelectionModel().select(sizes.get(3));
    }

    private void initFontComboBox() {
        ObservableList<String> fonts = FXCollections.observableList(Font.getFamilies());
        textFontComboBox.setItems(fonts);
        textFontComboBox.getSelectionModel().select(TextContainer.DEFAULT_FONT);
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
            NoteObjectControllerI controller = null;
            try {
                controller = StateHandler.getInstance().getState().getOnMousePressed(notePane, event);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            addNodeToNotePane(controller);
        });
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
            Note.getCurrentNote().addNoteObject(controller.getModel());
        }
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
        //gc.setFill(Color.WHITE);
        //gc.fillRect(brushPictureCanvas.getLayoutX(),brushPictureCanvas.getLayoutY(),brushPictureCanvas.getWidth(),brushPictureCanvas.getHeight());
        gc.setFill(Paintbrush.getColor());
        switch (PaintingContainer.getPaintbrush()) {
            case TRIANGLE:
                double[] xPoints = {brushPicture.getPrefWidth() / 2 - Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefWidth() / 2, brushPicture.getPrefWidth() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG};
                double[] yPoints = {brushPicture.getPrefHeight() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_SMALL, brushPicture.getPrefHeight() / 2 - Paintbrush.getSize() * TRIANGLE_QUANTIFIER_BIG, brushPicture.getPrefHeight() / 2 + Paintbrush.getSize() * TRIANGLE_QUANTIFIER_SMALL};
                gc.fillPolygon(xPoints, yPoints, TRIANGLE_NUMBER_OF_CORNERS);
                break;
            case SQUARE:
                gc.fillRect(brushPicture.getPrefWidth() / 2 - (Paintbrush.getSize() / 2), brushPicture.getPrefHeight() / 2 - (Paintbrush.getSize() / 2), Paintbrush.getSize(), Paintbrush.getSize());
                break;
            case CIRCLE:
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
            TextContainer.setIsItalic(true);
        } else {
            TextContainer.setIsItalic(false);
        }
    }

    @FXML
    private void makeBold() {
        if (boldToggleButton.isSelected()) {
            TextContainer.setIsBold(true);
        } else {
            TextContainer.setIsBold(false);
        }
    }

    @FXML
    private void changeFont() {
        TextContainer.setFontFamilyName((String) textFontComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void changeSize() {
        TextContainer.setFontSize((int) textSizeComboBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void importImage() {
        FileChooser fileChooser = FileChooserFactory.getImageChooser();
        File image = fileChooser.showOpenDialog(notePane.getScene().getWindow());
        try {
            NoteObjectControllerI controller = new ImageContainerController(image.toURI().toURL(), 0, 0);
            Note.getCurrentNote().addNoteObject(controller.getModel());
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
            File file = FileHandler.saveNote(new NoteSave(Note.getCurrentNote()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadNote() {
        FileChooser fileChooser = FileChooserFactory.getFabNotesChooser();
        File file = fileChooser.showOpenDialog(this.notePane.getScene().getWindow());
        try {
            NoteSave noteSave = FileHandler.loadNote(file);
            this.loadNoteSave(noteSave);
        } catch (IOException e) {
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
