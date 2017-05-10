package controllers.noteobject;


import controllers.fxml.MainPageController;

import javafx.scene.text.Font;

import models.noteobject.TextContainer;
import utilities.ObserverI;
import state.WriteState;
import views.noteobject.RichTextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<RichTextContainerView> implements ObserverI<WriteState> {

    private final int PADDING = 30;

    private TextContainer textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new RichTextContainerView(text, layoutX, layoutY));
        this.textContainerModel = new TextContainer(text);
        this.textContainerModel.bindTextProperties(super.getNode().textProperty());
        //behöver fixa denna långa rad med kod
        WriteState writeState = WriteState.getInstance();
        //super.getNode().setFont(Font.font(writeState.getFontFamilyName(), writeState.getFontWeight(), writeState.getFontPosture(), writeState.getTextSize()));

        this.textContainerModel.setFont(Font.font(writeState.getFontFamilyName(), writeState.getTextSize()));
        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener((value, oldValue, newValue) -> {
            double newWidth = this.textContainerModel.getWidth() + this.PADDING + WriteState.getInstance().getTextSize(); // + 30 because padding
            double newHeight = this.textContainerModel.getHeight() + this.PADDING + WriteState.getInstance().getTextSize();
            super.getNode().changeTextContainerSize(newWidth, newHeight);
            super.getNode().changeBorderColour();
        });
    }

    private void listener2() {
        super.getNode().focusedProperty().addListener(e -> {
            super.getNode().changeFocus();
            if (!super.getNode().isFocused() && super.getNode().getText().equals("")) {
                MainPageController.getCurrentNote().removeNoteObject(super.getNode());
            }
        });
    }

    private String fontToCss(Font font) {
        return "-fx-font: " + font.getStyle().toLowerCase().replace("regular", "") +
                " " + ((int) font.getSize()) + "px \"" + font.getFamily() + "\"";
    }

    @Override
    public void fireChange(WriteState subject) {
        if (super.getNode().isFocused()){
            //super.getNode().setFont(Font.font(WriteState.getInstance().getFontFamilyName(), WriteState.getInstance().getTextSize()));
        }
    }

}
