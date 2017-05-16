package controllers.noteobject;

import javafx.scene.input.MouseEvent;
import models.note.Note;
import models.noteobject.PaintingContainer;
import services.ObserverBus;
import services.StateHandler;
import observers.ObserverI;
import controllers.noteobjectbehaviors.DragDropBehavior;
import controllers.noteobjectbehaviors.PaintingBehavior;
import state.PaintState;
import views.noteobject.PaintingContainerView;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView, PaintingContainer> implements ObserverI<StateHandler>{

    public PaintingContainerController(MouseEvent event) {
        super(new PaintingContainerView(), new PaintingContainer(event.getX(),event.getY()));
        fireChange(StateHandler.getInstance());
        super.getBehavior().onMousePressed(event);
        ObserverBus.addListener(super.getModel(),super.getNode());
        ObserverBus.addListener(StateHandler.getInstance(), this);
        focusPropertyListener();
    }

    public PaintingContainerController(PaintingContainer model){
        super(new PaintingContainerView(), new PaintingContainer(model));
        fireChange(StateHandler.getInstance());
        ObserverBus.addListener(super.getModel(),super.getNode());
        ObserverBus.addListener(StateHandler.getInstance(), this);
        focusPropertyListener();
    }

    public void focusPropertyListener(){
        super.getNode().focusedProperty().addListener(observable -> {
            if(!super.getNode().isFocused()){
                if(!super.getNode().getPaintStatus()){
                    super.getModel().remove();
                    Note.getCurrentNote().removeNoteObject(super.getModel());
                }
                super.getNode().removeBorder();
            } else {
                super.getNode().createBorder();
            }
        });
    }

    public void fireChange(StateHandler subject) {
        if (subject.isWriteState()){
            super.setBehavior(new DragDropBehavior(super.getModel(), super.getNode()));
        }else{
            super.setBehavior(new PaintingBehavior(super.getModel(),super.getNode()));
        }

    }

}
