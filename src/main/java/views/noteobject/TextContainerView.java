package views.noteobject;

import javafx.scene.control.TextArea;

import java.io.Serializable;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea implements Serializable {

    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(0);
        this.setPrefHeight(0);
        this.setWrapText(true);
    }

    public TextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }

    public void changeTextContainerSize(double newWidth, double newHeight) {
        this.setPrefWidth(newWidth);
        this.setPrefHeight(newHeight);
    }

}


