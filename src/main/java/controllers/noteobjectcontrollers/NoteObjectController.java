package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import utilities.noteobjectbehaviors.NoteObjectBehaviorI;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController<T extends Node> implements NoteObjectControllerI {

    private T view;

    private NoteObjectBehaviorI noteObjectBehavior;

    private List<NoteObjectObserverI> listeners;

    NoteObjectController(T view) {
        this.view = view;
        this.listeners = new ArrayList<>();
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseEntered();
        setOnMouseExited();
        setOnMouseMoved();
        setOnMouseDragged();
    }

    void notifyListeners() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).fireChange(this);
        }
    }

    @Override
    public T getNode() {
        return view;
    }

    @Override
    public void addListener(NoteObjectObserverI noteObjectObserver) {
        listeners.add(noteObjectObserver);
    }

    @Override
    public void removeListener(NoteObjectObserverI noteObjectObserver) {
        listeners.remove(noteObjectObserver);
    }

    NoteObjectBehaviorI getBehavior() {
        return this.noteObjectBehavior;
    }

    void setBehavior(NoteObjectBehaviorI noteObjectBehavior) {
        this.noteObjectBehavior = noteObjectBehavior;
    }

    private void setOnMousePressed() {
        this.getNode().setOnMousePressed(mouseEvent -> {
            noteObjectBehavior.onMousePressed(mouseEvent);
        });
    }

    private void setOnMouseReleased() {
        this.getNode().setOnMouseReleased(mouseEvent -> {
            noteObjectBehavior.onMouseReleased(mouseEvent);
        });
    }

    private void setOnMouseEntered() {
        this.getNode().setOnMouseEntered(mouseEvent -> {
            noteObjectBehavior.onMouseEntered(mouseEvent);
        });
    }

    private void setOnMouseExited() {
        this.getNode().setOnMouseExited(mouseEvent -> {
            noteObjectBehavior.onMouseExited(mouseEvent);
        });
    }

    private void setOnMouseMoved() {
        this.getNode().setOnMouseMoved(mouseEvent -> {
            noteObjectBehavior.onMouseMoved(mouseEvent);
        });
    }

    private void setOnMouseDragged() {
        this.getNode().setOnMouseDragged(mouseEvent -> {
            noteObjectBehavior.onMouseDragged(mouseEvent);
        });
    }

}
