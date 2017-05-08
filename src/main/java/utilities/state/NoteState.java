package utilities.state;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by aron on 2017-05-04.
 */
abstract class NoteState implements NoteStateI {
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
}
