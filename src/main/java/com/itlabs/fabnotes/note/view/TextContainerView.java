package com.itlabs.fabnotes.note.view;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.TextContainer;
import com.itlabs.fabnotes.note.utility.observer.ObserverI;

import java.util.regex.Pattern;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerView extends TextArea implements ObserverI<TextContainer> {

    private Text textHolder;
    private String styles;

    private final static String DEFAULT_BACKGROUND = "-fx-background-color: transparent";
    private final static String VISABLE_BORDER = "-fx-border-color: lightskyblue";
    private final static String INVISABLE_BORDER = "-fx-border-color: transparent";

    private final static String STYLE_SPLITTER = ";";
    
    public TextContainerView(String text, double layoutX, double layoutY) {
        super(text);
        this.textHolder = new Text(text);
        this.setLayoutX(layoutX);
        this.setLayoutY(layoutY);
        this.setPrefWidth(0);
        this.setPrefHeight(0);
        updateTextContainerSize();
        this.setWrapText(true);
        this.styles = DEFAULT_BACKGROUND + STYLE_SPLITTER;
        changeBorder();
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
        style = style.replace(STYLE_SPLITTER, "");
        if (!this.styles.contains(style)) {
            this.styles += style + STYLE_SPLITTER;
        }
    }

    private void removeStyle(String style) {
        style = style.replace(STYLE_SPLITTER, "");
        this.styles = this.styles.replace(style + STYLE_SPLITTER, "");
    }

    public void switchToVisibleBorder() {
        this.removeStyle(INVISABLE_BORDER);
        this.addStyle(VISABLE_BORDER);
        this.setStyle(this.styles);
    }

    public void switchToInvisibleBorder() {
        this.removeStyle(VISABLE_BORDER);
        this.addStyle(INVISABLE_BORDER);
        this.setStyle(this.styles);
    }

    public void changeBorder() {
        if (this.getText().equals("") || !this.isFocused()) {
            switchToInvisibleBorder();
        } else {
            switchToVisibleBorder();
        }
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


