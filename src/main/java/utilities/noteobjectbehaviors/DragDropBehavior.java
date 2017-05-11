package utilities.noteobjectbehaviors;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import utilities.events.Event;
import utilities.events.MovedNoteEvent;

/**
 * Created by aron on 2017-05-03.
 */
public class DragDropBehavior implements NoteObjectBehaviorI {

    private Node view;

    private double dragx, dragy;

    public DragDropBehavior(Node view) {
        this.view = view;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        view.requestFocus();
        dragx = mouseEvent.getX();
        dragy = mouseEvent.getY();
        Event.addEvent(new MovedNoteEvent(view,dragx,dragy));
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
    public void onMouseMoved(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        double newX = view.getLayoutX() + mouseEvent.getX() - dragx;
        double newY = view.getLayoutY() + mouseEvent.getY() - dragy;
        if (newX >= 0) {
            view.setLayoutX(newX);
        }
        if (newY >= 0) {
            view.setLayoutY(newY);
        }
    }
}
