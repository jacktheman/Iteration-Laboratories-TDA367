package controllers.noteobjectbehaviors;

import events.Event;
import events.PaintingEvent;
import javafx.scene.input.MouseEvent;
import models.noteobject.PaintingContainer;
import utilities.PaintStrokeToData;
import views.noteobject.PaintingContainerView;

/**
 * Created by jackflurry on 2017-05-03.
 */
public class PaintingBehavior extends NoteObjectBehavior {

    private PaintingContainer model;
    private PaintingContainerView view;
    private PaintStrokeToData paintStroke;


    public PaintingBehavior(PaintingContainer model, PaintingContainerView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        paintStroke = new PaintStrokeToData();
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
        model.addPainting(paintStroke);
        Event.addEvent(new PaintingEvent(model));
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        view.createPaintStroke(mouseEvent.getX(),mouseEvent.getY(),paintStroke);
        model.paintingSizeCounter(mouseEvent.getX(),mouseEvent.getY());
    }


}

