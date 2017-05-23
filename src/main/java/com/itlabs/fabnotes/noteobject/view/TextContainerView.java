package com.itlabs.fabnotes.noteobject.view;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.noteobject.model.TextContainer;
import com.itlabs.fabnotes.noteobject.utility.observer.ObserverI;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea implements ObserverI<TextContainer> {

    private Text textHolder;
    private String styles;

    private final static String DEFAULT_BACKGROUND = "-fx-background-color: transparent";
    private final static String VISABLE_BORDER = "-fx-border-color: lightskyblue";
    private final static String INVISABLE_BORDER = "-fx-border-color: transparent";

    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.textHolder = new Text(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(0);
        this.setPrefHeight(0);
        updateTextContainerSize();
        this.setWrapText(true);
        this.styles = DEFAULT_BACKGROUND + ";";
        changeBorder();
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

    private void addStyle(String style) {
        style = style.replace(";", "");
        if (!this.styles.contains(style)) {
            this.styles += style + ";";
        }
    }

    private void removeStyle(String style) {
        style = style.replace(";", "");
        this.styles = this.styles.replace(style + ";", "");
    }

    private void switchToVisibleBorder() {
        this.removeStyle(INVISABLE_BORDER);
        this.addStyle(VISABLE_BORDER);
    }

    private void switchToInvisibleBorder() {
        this.removeStyle(VISABLE_BORDER);
        this.addStyle(INVISABLE_BORDER);
    }

    public void changeBorder() {
        if (this.getText().equals("") || !this.isFocused()) {
            switchToInvisibleBorder();
        } else {
            switchToVisibleBorder();
        }
        this.setStyle(this.styles);
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

