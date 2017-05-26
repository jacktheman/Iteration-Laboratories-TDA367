package com.itlabs.fabnotes.note.behavior;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.NoteObjectResizeableI;

/**
 * Created by svante on 2017-05-04.
 */
public class DragDropResizeBehavior extends NoteObjectBehavior {

    private NoteObjectBehaviorI dragDrop;
    private NoteObjectBehaviorI resize;

    public DragDropResizeBehavior(NoteObjectResizeableI model, ImageView imageView){
        dragDrop = new DragDropBehavior(model, imageView);
        resize = new ResizeBehavior(model, imageView);
    }


    @Override
    public void onMousePressed(MouseEvent mouseEvent) {
        resize.onMousePressed(mouseEvent);
        if(ResizeBehavior.getPos() == null){
            dragDrop.onMousePressed(mouseEvent);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent mouseEvent) {
        dragDrop.onMouseReleased(mouseEvent);
        resize.onMouseReleased(mouseEvent);
    }

    @Override
    public void onMouseEntered(MouseEvent mouseEvent) {
        dragDrop.onMouseEntered(mouseEvent);
    }

    @Override
    public void onMouseExited(MouseEvent mouseEvent) {
        dragDrop.onMouseExited(mouseEvent);
    }

    @Override
    public void onMouseMoved(MouseEvent mouseEvent) {
        resize.onMouseMoved(mouseEvent);
    }

    @Override
    public void onMouseDragged(MouseEvent mouseEvent) {
        if(ResizeBehavior.getPos() == null){
            dragDrop.onMouseDragged(mouseEvent);
        } else {
            resize.onMouseDragged(mouseEvent);
        }
    }
}
