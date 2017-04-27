package controllers.noteobjectcontrollers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

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
        drag();
    }

    private void drag() {
        view.setOnMousePressed(mouseEvent -> {
            view.requestFocus();
            dragx = mouseEvent.getX();
            dragy = mouseEvent.getY();
            System.out.println("ddd");
            view.getScene().setCursor(Cursor.MOVE);
        });
        view.setOnMouseReleased(mouseEvent -> view.getScene().setCursor(Cursor.HAND));
        view.setOnMouseDragged(mouseEvent -> {
            view.setLayoutX(view.getLayoutX() + mouseEvent.getX() - dragx);
            view.setLayoutY(view.getLayoutY() + mouseEvent.getY() - dragy);
        });
        view.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.HAND);
            }
        });
        view.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                view.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }


    private void onRightClick() {

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
