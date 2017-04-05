package NoteObjects.Objects;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainer extends NoteObject {

    /**
     * The actual area in which the user writes.
     */
    private TextArea textArea;

    /**
     * A Text object for resizing the TextArea.
     *
     * Purely for logic. The TextArea contains a ScrollPane,
     * which makes it so that we can't resize it dynamically
     * without extra information about the contents size on screen.
     */
    private Text textHolder;

    public TextContainer(int xPos, int yPos) {
        super(xPos, yPos);
        textArea = new TextArea();
        textHolder = new Text();
        textArea.setPrefWidth(50);
        textArea.setPrefHeight(50);

        textArea.setWrapText(true);
        textHolder.textProperty().bind(textArea.textProperty());

        initPane();
    }

    private void listener() {
        textArea.textProperty().addListener(e -> {
            int newWidth = (int) textHolder.getLayoutBounds().getWidth() + 30; // + 30 because padding
            int newHeight = (int) textHolder.getLayoutBounds().getHeight() + 30;
            if (newWidth != textArea.getPrefWidth())
                textArea.setPrefWidth(newWidth);
            if (newHeight != textArea.getPrefHeight())
                textArea.setPrefHeight(newHeight);
        });
    }

    private void listener2() {
        textArea.focusedProperty().addListener(e -> {
            if (!textArea.isFocused() && textArea.getText().equals("")) {
                removeFromPane(textArea);
            }
        });
    }

    @Override
    void initPane() {
        listener();
        listener2();
        addToPane(textArea);
    }
}
