package models.noteobject;

import javafx.beans.value.ObservableValue;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by aron on 2017-04-03.
 */
public class TextContainer {

    private Text textHolder;

    public TextContainer(String text) {
        textHolder = new Text(text);
    }

    public void bindTextProperties(ObservableValue<? extends String> textProperty) {
        textHolder.textProperty().bind(textProperty);
    }

    public double getWidth() {
        return textHolder.getLayoutBounds().getWidth();
    }

    public double getHeight() {
        return textHolder.getLayoutBounds().getHeight();
    }

    public void setFont(Font font) {
        textHolder.setFont(font);
    }
}
