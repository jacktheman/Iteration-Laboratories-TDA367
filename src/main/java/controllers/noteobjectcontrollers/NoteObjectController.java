package controllers.noteobjectcontrollers;

import javafx.scene.Cursor;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController<T extends Node> implements NoteObjectControllerI {

    private T view;

    private List<NoteObjectObserverI> listeners;

    private double dragx, dragy;

    NoteObjectController(T view) {
        this.view = view;
        this.listeners = new ArrayList<>();
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseEntered();
        setOnMouseExited();
        setOnMouseDragged();
    }

    private void setOnMousePressed() {
        view.setOnMousePressed(mouseEvent -> {
            view.requestFocus();
            dragx = mouseEvent.getX();
            dragy = mouseEvent.getY();
            view.getScene().setCursor(Cursor.MOVE);
            onMousePressed();
        });
    }

    private void setOnMouseReleased() {
        view.setOnMouseReleased(mouseEvent -> {
            view.getScene().setCursor(Cursor.HAND);
            onMouseReleased();
        });
    }

    private void setOnMouseEntered() {
        view.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.HAND);
            }
        });
    }

    private void setOnMouseExited() {
        view.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    private void setOnMouseDragged() {
        view.setOnMouseDragged(mouseEvent -> {
            double newX = view.getLayoutX() + mouseEvent.getX() - dragx;
            double newY = view.getLayoutY() + mouseEvent.getY() - dragy;
            if (newX >= 0) {
                view.setLayoutX(newX);
            }
            if (newY >= 0) {
                view.setLayoutY(newY);
            }

        });
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


}
