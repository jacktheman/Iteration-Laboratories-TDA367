package com.itlabs.fabnotes.noteobject.view;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.noteobject.model.TextContainer;
import com.itlabs.fabnotes.noteobject.observers.ObserverI;

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
        updateTextContainerSize();
        this.setWrapText(true);
    }

    public TextContainerView(double layoutX, double layoutY) {
        this("", layoutX, layoutY);
    }

    public void updateTextContainerSize() {
        if (!this.textProperty().isBound()) {
            textHolder.textProperty().bind(this.textProperty());
            textHolder.setFont(this.getFont());
        }

        double newWidth = this.textHolder.getLayoutBounds().getWidth() + TextContainer.PADDING + this.getFont().getSize();
        double newHeight = this.textHolder.getLayoutBounds().getHeight() + TextContainer.PADDING + this.getFont().getSize();

        this.setPrefWidth(newWidth);
        this.setPrefHeight(newHeight);
    }

    @Override
    public void fireChange(TextContainer subject) {
        if (Note.getCurrentNodes().contains(this))
            Note.getCurrentNodes().remove(this);
        else {
            Note.getCurrentNodes().add(this);
            this.requestFocus();
        }
    }
}


