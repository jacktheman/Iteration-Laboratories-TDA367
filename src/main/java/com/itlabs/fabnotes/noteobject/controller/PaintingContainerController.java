package com.itlabs.fabnotes.noteobject.controller;

import javafx.scene.input.MouseEvent;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.noteobject.model.PaintingContainer;
import com.itlabs.fabnotes.noteobject.service.ObserverBus;
import com.itlabs.fabnotes.noteobject.behavior.DragDropBehavior;
import com.itlabs.fabnotes.noteobject.behavior.PaintingBehavior;
import com.itlabs.fabnotes.noteobject.view.PaintingContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-04-07.
 */
public class PaintingContainerController extends NoteObjectController<PaintingContainerView, PaintingContainer> {

    private static List<PaintingContainerController> controllers = new ArrayList<>();

    public PaintingContainerController(MouseEvent event) {
        super(new PaintingContainerView(), new PaintingContainer(event.getX(),event.getY()));
        super.setBehavior(new PaintingBehavior(getModel(), getNode()));
        super.getBehavior().onMousePressed(event);
        ObserverBus.addListener(super.getModel(),super.getNode());
        controllers.add(this);
        focusPropertyListener();
    }

    public PaintingContainerController(PaintingContainer model){
        super(new PaintingContainerView(), new PaintingContainer(model));
        ObserverBus.addListener(super.getModel(),super.getNode());
        controllers.add(this);
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

    public static void changeToWriteState() {
        if (!controllers.isEmpty()) {
            removeDeadControllers();
            for (PaintingContainerController controller : controllers)
                controller.setBehavior(new DragDropBehavior(controller.getModel(), controller.getNode()));
        }
    }

    public static void changeToPaintState() {
        if (!controllers.isEmpty()) {
            removeDeadControllers();
            for (PaintingContainerController controller : controllers)
                controller.setBehavior(new PaintingBehavior(controller.getModel(), controller.getNode()));
        }
    }

    private static void removeDeadControllers() {
        List<PaintingContainerController> deadControllers = new ArrayList<>();
        for (PaintingContainerController controller : controllers) {
            if (!controller.getModel().getStatus())
                deadControllers.add(controller);
        }
        controllers.removeAll(deadControllers);
    }

    @Override
    public PaintingContainerController clone() {
        return new PaintingContainerController(super.getModel());
    }

}
