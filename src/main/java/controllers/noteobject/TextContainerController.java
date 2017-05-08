package controllers.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.control.MenuItem;
import models.noteobject.TextContainer;
import views.noteobject.TextContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-04-06.
 */
public class TextContainerController extends NoteObjectController<TextContainerView> {

    private TextContainer textContainerModel;

    public TextContainerController(String text, double layoutX, double layoutY) {
        super(new TextContainerView(text, layoutX, layoutY));
        this.textContainerModel = new TextContainer(text);
        this.textContainerModel.bindTextProperties(super.getNode().textProperty());

        listener();
        listener2();
    }

    private void listener() {
        super.getNode().textProperty().addListener(e -> {
            double newWidth = this.textContainerModel.getWidth() + 30; // + 30 because padding
            double newHeight = this.textContainerModel.getHeight() + 30;
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
    List<MenuItem> initContextMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();
        return menuItemList;
    }
}
