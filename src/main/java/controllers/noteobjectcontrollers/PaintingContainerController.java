package controllers.noteobjectcontrollers;

import services.ObserverBus;
import services.StateHandler;
import utilities.ObservableI;
import utilities.ObserverI;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.NoteObjectBehaviorI;
import utilities.noteobjectbehaviors.PaintingBehavior;
import utilities.state.NoteStateI;
import utilities.state.PaintState;
import utilities.state.WriteState;
import views.noteobjectviews.PaintingContainerView;

import java.awt.*;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView> implements ObserverI<StateHandler>{

    public PaintingContainerController(double x, double y) {
        super(new PaintingContainerView(x,y));
        super.setBehavior(new PaintingBehavior(super.getNode()));
        ObserverBus.addListener(StateHandler.getInstance(), this);
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
