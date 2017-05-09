package controllers.noteobject;


import controllers.fxml.MainPageController;
import javafx.scene.control.MenuItem;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

import models.noteobject.TextContainer;
import utilities.ObserverI;
import utilities.state.WriteState;
import views.noteobject.TextContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView> implements ObserverI<WriteState> {

    private TextContainer textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textContainerModel = new TextContainer(text);
        this.textContainerModel.bindTextProperties(super.getNode().textProperty());
        //behöver fixa denna långa rad med kod
        super.getNode().setFont(Font.font(WriteState.getInstance().getFontFamilyName(), WriteState.getInstance().getFontWeight(), WriteState.getInstance().getFontPosture(), WriteState.getInstance().getTextSize()));

        this.textContainerModel.setFont(Font.font(WriteState.getInstance().getFontFamilyName(), WriteState.getInstance().getTextSize()));
        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener(e -> {
            double newWidth = this.textContainerModel.getWidth() + 30 + WriteState.getInstance().getTextSize(); // + 30 because padding
            double newHeight = this.textContainerModel.getHeight() + 30 + WriteState.getInstance().getTextSize();
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

    @Override
    public void fireChange(WriteState subject) {
        if (super.getNode().isFocused()){
            super.getNode().setFont(Font.font(WriteState.getInstance().getFontFamilyName(), WriteState.getInstance().getTextSize()));
        }
    }

}
