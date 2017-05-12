package controllers.noteobject;

import javafx.scene.input.MouseEvent;
import models.note.Note;
import models.noteobject.PaintingContainer;
import services.ObserverBus;
import services.StateHandler;
import utilities.ObserverI;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.PaintingBehavior;
import controllers.state.PaintState;
import views.noteobject.PaintingContainerView;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView, PaintingContainer> implements ObserverI<StateHandler>{

    public PaintingContainerController(MouseEvent event) {
        super(new PaintingContainerView(event.getX(),event.getY()), null);
        super.setBehavior(new PaintingBehavior(super.getNode()));
        super.getBehavior().onMousePressed(event);
        ObserverBus.addListener(StateHandler.getInstance(), this);
        focusPropertyListener();
    }

    public void focusPropertyListener(){
        super.getNode().focusedProperty().addListener(observable -> {
            if(!super.getNode().isFocused()){
                if(!super.getNode().getPaintStatus()){
                    Note.getCurrentNote().removeNoteObject(super.getNode());
                }
                super.getNode().removeBorder();
            } else {
                super.getNode().createBorder();
            }
        });
    }

    @Override
    public void fireChange(StateHandler subject) {
        if (subject.getState().equals(PaintState.getInstance())){
            super.setBehavior(new PaintingBehavior(super.getNode()));
        }else{
            super.setBehavior(new DragDropBehavior(super.getNode()));
        }

    }

}
