package controllers.noteobjectcontrollers;

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

    private NoteObjectBehaviorI behavior;

    public PaintingContainerController(double x, double y) {
        super(new PaintingContainerView(x,y));
        behavior = new PaintingBehavior(super.getNode());
        StateHandler.getInstance().addListener(this);
    }

    @Override
    public void fireChange(StateHandler subject) {
        System.out.print(subject.toString());
        if (subject.getState().equals(PaintState.getInstance())){
                behavior = new PaintingBehavior(super.getNode());
        }else{
            behavior = new DragDropBehavior(super.getNode());
        }

    }
}
