package com.itlabs.fabnotes.fxml.state;

import com.itlabs.fabnotes.note.controller.ImageContainerController;
import com.itlabs.fabnotes.note.controller.PaintingContainerController;
import com.itlabs.fabnotes.note.controller.TextContainerController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.ImageContainer;
import com.itlabs.fabnotes.note.model.NoteObjectI;
import com.itlabs.fabnotes.note.model.PaintingContainer;
import com.itlabs.fabnotes.note.model.TextContainer;
import com.itlabs.fabnotes.note.service.ContextMenuFactory;

import java.net.MalformedURLException;

/**
 * Created by aron on 2017-05-04.
 */
abstract class NoteState implements NoteStateI {

    private ContextMenu contextMenu;

    boolean pressedFocusOwner(AnchorPane notePane, MouseEvent event) {
        Node focusOwner = notePane.getScene().getFocusOwner();
        if (notePane.getChildren().contains(focusOwner)) {
            double x1 = focusOwner.getLayoutX();
            double x2 = focusOwner.getLayoutX() + focusOwner.getBoundsInLocal().getWidth();
            double y1 = focusOwner.getLayoutY();
            double y2 = focusOwner.getLayoutY() + focusOwner.getBoundsInLocal().getHeight();

            if ((event.getX() >= x1 && event.getX() <= x2 && event.getY() >= y1 && event.getY() <= y2)) {
                return true;
            }
        }
        return false;
    }

    private void pasteOnCanvas(MouseEvent event) {
        NoteObjectI model = initNewNoteObjectController(ContextMenuFactory.getCopiedObject());
        if (model != null) {
            model.setLayoutX(event.getX());
            model.setLayoutY(event.getY());
            Note.getCurrentNote().addNoteObject(model);
        }
    }

    private NoteObjectI initNewNoteObjectController(NoteObjectI noteObject) {
        if (noteObject instanceof TextContainer)
            return (new TextContainerController((TextContainer) noteObject)).getModel();
        else if (noteObject instanceof ImageContainer)
            return (new ImageContainerController((ImageContainer) noteObject).getModel());
        else if (noteObject instanceof PaintingContainer)
            return (new PaintingContainerController((PaintingContainer) noteObject).getModel());
        return null;
    }

    public ContextMenu getContextMenu() {
        return this.contextMenu;
    }

    @Override
    public void getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        MenuItem paste = new MenuItem("Klistra in");
        paste.setOnAction(actionEvent -> {
            pasteOnCanvas(event);
        });
        if (!pressedFocusOwner(notePane, event)) {
            if (event.getSource().equals(notePane) && event.getButton().equals(MouseButton.SECONDARY)) {
                contextMenu = new ContextMenu(paste);
                contextMenu.show(notePane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    @Override
    public void getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
    }

}
