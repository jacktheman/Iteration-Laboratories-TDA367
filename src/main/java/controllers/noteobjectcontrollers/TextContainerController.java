package controllers.noteobjectcontrollers;

import models.noteobjectmodels.TextContainerModel;
import views.noteobjectviews.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView> {

    private TextContainerModel textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textContainerModel = new TextContainerModel(text);
        this.textContainerModel.getTextHolder().textProperty().bind(super.getNode().textProperty());

        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener(e -> {
            double newWidth = this.textContainerModel.getTextHolder().getLayoutBounds().getWidth() + 30; // + 30 because padding
            double newHeight = this.textContainerModel.getTextHolder().getLayoutBounds().getHeight() + 30;
            super.getNode().changeTextContainerSize(newWidth, newHeight);
            super.getNode().changeBorderColour();
        });
    }

    private void listener2() {
        super.getNode().focusedProperty().addListener(e -> {
            super.getNode().changeFocus();
            if (!super.getNode().isFocused() && super.getNode().getText().equals("")) {
                super.notifyListeners();
            }
        });
    }
}
