package controllers.noteobject;

import javafx.fxml.FXML;
import services.ObserverBus;
import services.StateHandler;
import utilities.ObserverI;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.PaintingBehavior;
import utilities.state.PaintState;
import views.noteobject.PaintingContainerView;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView> implements ObserverI<StateHandler>{

    public PaintingContainerController(double x, double y) {
        super(new PaintingContainerView(x,y));
        super.setBehavior(new PaintingBehavior(super.getNode()));
        ObserverBus.addListener(StateHandler.getInstance(), this);
        focusPropertyListener();
    }

    public void focusPropertyListener(){
        super.getNode().focusedProperty().addListener(observable -> {
            if(!super.getNode().isFocused()){
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
