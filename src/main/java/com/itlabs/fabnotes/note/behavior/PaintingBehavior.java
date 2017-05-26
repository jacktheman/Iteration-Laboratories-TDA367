package com.itlabs.fabnotes.note.behavior;

import javafx.scene.input.MouseEvent;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.PaintingContainer;
import com.itlabs.fabnotes.note.utility.paint.PaintStrokeToData;
import com.itlabs.fabnotes.note.view.PaintingContainerView;

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
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        view.createPaintStroke(mouseEvent.getX(),mouseEvent.getY(),paintStroke);
        model.paintingSizeCounter(mouseEvent.getX(),mouseEvent.getY());
    }


}

