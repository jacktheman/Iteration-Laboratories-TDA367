package controllers.noteobject;

import controllers.fxml.MainPageController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import services.ObserverBus;
import services.StateHandler;
import utilities.ObserverI;
import utilities.noteobjectbehaviors.DragDropBehavior;
import utilities.noteobjectbehaviors.PaintingBehavior;
import utilities.state.PaintState;
import views.noteobject.PaintingContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView> implements ObserverI<StateHandler>{

    public PaintingContainerController(MouseEvent event) {
        super(new PaintingContainerView(event.getX(),event.getY()));
        super.setBehavior(new PaintingBehavior(super.getNode()));
        super.getBehavior().onMousePressed(event);
        ObserverBus.addListener(StateHandler.getInstance(), this);
        focusPropertyListener();
    }

    public void focusPropertyListener(){
        super.getNode().focusedProperty().addListener(observable -> {
            if(!super.getNode().isFocused()){
                if(!super.getNode().getPaintStatus()){
                    MainPageController.getCurrentNote().removeNoteObject(super.getNode());
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

    @Override
    List<MenuItem> initContextMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();
        return menuItemList;
    }
}
