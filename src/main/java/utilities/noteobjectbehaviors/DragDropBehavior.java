package utilities.noteobjectbehaviors;

import javafx.scene.Cursor;
import javafx.scene.Node;

/**
 * Created by aron on 2017-05-03.
 */
public class DragDropBehavior implements NoteObjectBehaviorI {

    private Node view;

    private double dragx, dragy;

    public DragDropBehavior(Node view) {
        this.view = view;
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseEntered();
        setOnMouseExited();
        setOnMouseDragged();
    }

    private void setOnMousePressed() {
        view.setOnMousePressed(mouseEvent -> {
            view.requestFocus();
            dragx = mouseEvent.getX();
            dragy = mouseEvent.getY();
            view.getScene().setCursor(Cursor.MOVE);
        });
    }

    private void setOnMouseReleased() {
        view.setOnMouseReleased(mouseEvent -> {
            view.getScene().setCursor(Cursor.HAND);
        });
    }

    private void setOnMouseEntered() {
        view.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.HAND);
            }
        });
    }

    private void setOnMouseExited() {
        view.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    private void setOnMouseDragged() {
        view.setOnMouseDragged(mouseEvent -> {
            double newX = view.getLayoutX() + mouseEvent.getX() - dragx;
            double newY = view.getLayoutY() + mouseEvent.getY() - dragy;
            if (newX >= 0) {
                view.setLayoutX(newX);
            }
            if (newY >= 0) {
                view.setLayoutY(newY);
            }

        });
    }
}
