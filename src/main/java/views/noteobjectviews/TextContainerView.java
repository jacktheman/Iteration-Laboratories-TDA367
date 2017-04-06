package views.noteobjectviews;

import javafx.scene.control.TextArea;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea {
    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
    }

    public TextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }
}
