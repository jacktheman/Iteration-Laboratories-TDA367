package views.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import models.noteobject.TextContainer;
import utilities.ObserverI;

import java.io.Serializable;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea implements ObserverI<TextContainer> {

    private Text textHolder;

    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.textHolder = new Text(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(0);
        this.setPrefHeight(0);
        textHolder.textProperty().bind(this.textProperty());
        updateTextContainerSize();
        this.setWrapText(true);
    }

    public TextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }

    public void updateTextContainerSize() {
        double newWidth = this.textHolder.getLayoutBounds().getWidth() + TextContainer.PADDING + this.getFont().getSize();
        double newHeight = this.textHolder.getLayoutBounds().getHeight() + TextContainer.PADDING + this.getFont().getSize();

        this.setPrefWidth(newWidth);
        this.setPrefHeight(newHeight);
    }

    @Override
    public void fireChange(TextContainer subject) {
        if (MainPageController.getCurrentNodes().contains(this))
            MainPageController.getCurrentNodes().remove(this);
        else {
            MainPageController.getCurrentNodes().add(this);
            this.requestFocus();
        }
    }
}


