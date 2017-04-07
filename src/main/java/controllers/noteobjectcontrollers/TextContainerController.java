package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import models.noteobjectmodels.TextContainerModel;
import views.noteobjectviews.TextContainerView;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController{

    private TextContainerView textArea;
    private TextContainerModel textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textArea = (TextContainerView) super.getNode();
        this.textContainerModel = new TextContainerModel(text);
        this.textContainerModel.getTextHolder().textProperty().bind(textArea.textProperty());

        listener();
        listener2();
    }

    @Override
    public Node getNode() {
        return textArea;
    }


    private void listener() {
        textArea.textProperty().addListener(e -> {
            double newWidth = textContainerModel.getTextHolder().getLayoutBounds().getWidth() + 30; // + 30 because padding
            double newHeight = textContainerModel.getTextHolder().getLayoutBounds().getHeight() + 30;
            textArea.changeTextContainerSize(newWidth, newHeight);
            textArea.changeBorderColour();
        });
    }

    private void listener2() {
        textArea.focusedProperty().addListener(e -> {
            textArea.changeFocus();
            if (!textArea.isFocused() && textArea.getText().equals("")) {
                this.notifyListeners();
            }
        });
    }
}
