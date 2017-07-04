package com.itlabs.fabnotes.fxml.state;

import com.itlabs.fabnotes.note.controller.ImageContainerController;
import com.itlabs.fabnotes.note.controller.PaintingContainerController;
import com.itlabs.fabnotes.note.controller.TableContainerController;
import com.itlabs.fabnotes.note.controller.TextContainerController;
import com.itlabs.fabnotes.note.model.*;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.itlabs.fabnotes.note.service.ContextMenuFactory;

import java.net.MalformedURLException;

/**
 * Created by aron on 2017-05-04.
 */
abstract class NoteState implements NoteStateI {

    private static final String PASTE = "Klistra in";

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
        else if (noteObject instanceof TableContainer)
            return (new TableContainerController((TableContainer) noteObject).getModel());
        return null;
    }

    @Override
    public void getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        if (!pressedFocusOwner(notePane, event)) {
            if (event.getSource().equals(notePane) && event.getButton().equals(MouseButton.SECONDARY)) {
                MenuItem paste = new MenuItem(PASTE);
                paste.setOnAction(actionEvent -> {
                    pasteOnCanvas(event);
                });
                contextMenu = new ContextMenu(paste);
                contextMenu.show(notePane, event.getScreenX(), event.getScreenY());
            }
        }
    }

    @Override
    public void getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
    }

}
