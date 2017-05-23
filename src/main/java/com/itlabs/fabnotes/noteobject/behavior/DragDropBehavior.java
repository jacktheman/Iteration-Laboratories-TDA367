package com.itlabs.fabnotes.noteobject.behavior;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.note.event.Event;
import com.itlabs.fabnotes.note.event.MovedNoteEvent;

/**
 * Created by aron on 2017-05-03.
 */
public class DragDropBehavior extends NoteObjectBehavior {

    private final Node view;
    private final NoteObjectI model;

    private double dragx, dragy;

    public DragDropBehavior(NoteObjectI model ,Node view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        view.requestFocus();
        dragx = mouseEvent.getX();
        dragy = mouseEvent.getY();
        Event.addEvent(new MovedNoteEvent(model,dragx,dragy));
        view.getScene().setCursor(Cursor.MOVE);
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
        view.getScene().setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseEntered(MouseEvent mouseEvent) {
        if (!mouseEvent.isPrimaryButtonDown()) {
            view.getScene().setCursor(Cursor.HAND);
        }
    }

    @Override
    public void onMouseExited(MouseEvent mouseEvent) {

        if (!mouseEvent.isPrimaryButtonDown()) {
            view.getScene().setCursor(Cursor.DEFAULT);
        }
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        double newX = model.getLayoutX() + mouseEvent.getX() - dragx;
        double newY = model.getLayoutY() + mouseEvent.getY() - dragy;
        if (newX >= 0) {
            model.setLayoutX(newX);
        }
        if (newY >= 0) {
            model.setLayoutY(newY);
        }
    }
}
