package utilities.noteobjectbehaviors;

import models.noteobjectmodels.PaintingContainerModel;
import views.noteobjectviews.PaintingContainerView;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class PaintingBehavior implements NoteObjectBehaviorI {

    private PaintingContainerView view;

    public PaintingBehavior(PaintingContainerView view){
        this.view = view;
        setOnMouseDragged();
    }

    private void setOnMouseDragged() {
        view.setOnMouseDragged(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                view.paint(PaintingContainerModel.getPaintbrush(), mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }
}
