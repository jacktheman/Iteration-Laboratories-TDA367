package com.itlabs.fabnotes.note.controller;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.TextContainer;
import com.itlabs.fabnotes.note.view.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView, TextContainer> {

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY), new TextContainer(text, layoutX, layoutY));
        super.getNode().setFont(this.getFont());
        super.getModel().addListener(super.getNode());
        initTextPropertyListener();
        initFocusPropertyListener();
    }

    public TextContainerController(TextContainer textContainer) {
        super(new TextContainerView(textContainer.getText(), textContainer.getLayoutX(), textContainer.getLayoutY()),
                new TextContainer(textContainer));
        super.getNode().setFont(this.getFont());
        super.getModel().addListener(super.getNode());
        super.getNode().updateTextContainerSize();
        initTextPropertyListener();
        initFocusPropertyListener();
    }

    private void initTextPropertyListener() {
        super.getNode().textProperty().addListener((value, oldValue, newValue) -> {
            super.getModel().setText(newValue);
            super.getNode().changeBorder();
            super.getNode().updateTextContainerSize();
        });
    }

    private void initFocusPropertyListener() {
        super.getNode().focusedProperty().addListener(e -> {
            super.getModel().setIsFocused(super.getNode().isFocused());
            super.getNode().changeBorder();
            if (!super.getNode().isFocused() && super.getNode().getText().equals("")) {
                Note.getCurrentNote().removeNoteObject(super.getModel());
            }
        });
    }

    private Font getFont() {
        Font font;
        if (super.getModel().isBold() && super.getModel().isItalic())
            font = Font.font(super.getModel().getFontFamilyName(), FontWeight.BOLD, FontPosture.ITALIC, super.getModel().getFontSize());
        else if (super.getModel().isBold())
            font = Font.font(super.getModel().getFontFamilyName(), FontWeight.BOLD, super.getModel().getFontSize());
        else if (super.getModel().isItalic())
            font = Font.font(super.getModel().getFontFamilyName(), FontPosture.ITALIC, super.getModel().getFontSize());
        else
            font = Font.font(super.getModel().getFontFamilyName(), super.getModel().getFontSize());
        return font;
    }

}
