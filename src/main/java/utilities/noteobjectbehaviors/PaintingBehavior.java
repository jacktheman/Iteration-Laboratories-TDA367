package utilities.noteobjectbehaviors;

import javafx.scene.input.MouseEvent;
import models.noteobject.PaintingContainer;
import views.noteobject.PaintingContainerView;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class PaintingBehavior implements NoteObjectBehaviorI {

    private PaintingContainerView view;

    public PaintingBehavior(PaintingContainerView view){
        this.view = view;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        view.requestFocus();
        view.toFront();
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseMoved(MouseEvent mouseEvent) {
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            view.paint(PaintingContainer.getPaintbrush(), mouseEvent.getX(), mouseEvent.getY());
        }
    }

}
