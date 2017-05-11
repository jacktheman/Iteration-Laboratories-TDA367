package controllers.noteobject;

import models.note.Note;
import models.noteobject.TextContainer;
import views.noteobject.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView> {

    private TextContainer textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textContainerModel = new TextContainer(text);
        this.textContainerModel.bindTextProperties(super.getNode().textProperty());
        this.textContainerModel.addStyle(textContainerModel.getFont());
        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener((value, oldValue, newValue) -> {
            double newWidth = this.textContainerModel.getWidth() + TextContainer.PADDING + this.textContainerModel.getFontSize();
            double newHeight = this.textContainerModel.getHeight() + TextContainer.PADDING + this.textContainerModel.getFontSize();
            super.getNode().changeTextContainerSize(newWidth, newHeight);
            this.textContainerModel.changeBorder();
            super.getNode().setStyle(this.textContainerModel.getStyles());
        });
    }

    private void listener2() {
        super.getNode().focusedProperty().addListener(e -> {
            this.textContainerModel.setIsFocused(super.getNode().isFocused());
            this.textContainerModel.changeBorder();
            super.getNode().setStyle(this.textContainerModel.getStyles());
            if (!super.getNode().isFocused() && super.getNode().getText().equals("")) {
                Note.getCurrentNote().removeNoteObject(super.getNode());
            }
        });
    }

}
