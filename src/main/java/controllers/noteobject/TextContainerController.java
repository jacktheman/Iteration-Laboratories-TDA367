package controllers.noteobject;

import models.note.Note;
import models.noteobject.TextContainer;
import services.ObserverBus;
import views.noteobject.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView, TextContainer> {

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY), new TextContainer(text, layoutX, layoutY));
        super.getModel().bindTextProperties(super.getNode().textProperty());
        super.getModel().addStyle(super.getModel().getFont());
        ObserverBus.addListener(super.getModel(), super.getNode());
        listener();
        listener2();
    }

    public TextContainerController(TextContainer textContainerModel) {
        super(new TextContainerView(textContainerModel.getText(), textContainerModel.getLayoutX(), textContainerModel.getLayoutY()),
                textContainerModel);
        super.getModel().bindTextProperties(super.getNode().textProperty());
        super.getModel().addStyle(textContainerModel.getFont());
        ObserverBus.addListener(super.getModel(), super.getNode());
        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener((value, oldValue, newValue) -> {
            double newWidth = super.getModel().getWidth() + TextContainer.PADDING + super.getModel().getFontSize();
            double newHeight = super.getModel().getHeight() + TextContainer.PADDING + super.getModel().getFontSize();
            super.getNode().changeTextContainerSize(newWidth, newHeight);
            super.getModel().changeBorder();
            super.getNode().setStyle(super.getModel().getStyles());
            super.getModel().setText(newValue);
        });
    }

    private void listener2() {
        super.getNode().focusedProperty().addListener(e -> {
            super.getModel().setIsFocused(super.getNode().isFocused());
            super.getModel().changeBorder();
            super.getNode().setStyle(super.getModel().getStyles());
            if (!super.getNode().isFocused() && super.getNode().getText().equals("")) {
                Note.getCurrentNote().removeNoteObject(super.getModel());
            }
        });
    }

}
