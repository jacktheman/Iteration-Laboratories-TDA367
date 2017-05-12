package controllers.noteobjectbehaviors;

import javafx.scene.input.MouseEvent;
import models.noteobject.PaintingContainer;
import utilities.PaintStrokeToData;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class PaintingBehavior implements NoteObjectBehaviorI {

    private PaintingContainer model;
    private PaintStrokeToData paintStroke;


    public PaintingBehavior(PaintingContainer model){
        this.model = model;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        paintStroke = new PaintStrokeToData(mouseEvent.getX(),mouseEvent.getY());
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
        model.addPainting(paintStroke);
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
        paintStroke.createPaintStroke(mouseEvent.getX(),mouseEvent.getY());
    }


}

