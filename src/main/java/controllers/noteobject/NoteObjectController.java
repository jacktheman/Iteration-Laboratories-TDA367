package controllers.noteobject;

import controllers.fxml.MainPageController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import services.NoteObjectCloner;
import utilities.noteobjectbehaviors.NoteObjectBehaviorI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController<T extends Node> implements NoteObjectControllerI {

    private T view;

    private NoteObjectBehaviorI noteObjectBehavior;

    private ContextMenu contextMenu;


    NoteObjectController(T view) {
        this.view = view;
        this.contextMenu = new ContextMenu();
        loadNewContextMenu();
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseEntered();
        setOnMouseExited();
        setOnMouseMoved();
        setOnMouseDragged();
    }


    @Override
    public T getNode() {
        return view;
    }

    NoteObjectBehaviorI getBehavior() {
        return this.noteObjectBehavior;
    }

    void setBehavior(NoteObjectBehaviorI noteObjectBehavior) {
        this.noteObjectBehavior = noteObjectBehavior;
    }

    private void setOnMousePressed() {
        this.getNode().setOnMousePressed(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMousePressed(mouseEvent);
            if(mouseEvent.isSecondaryButtonDown())
                contextMenu.show(view, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        });
    }

    private void setOnMouseReleased() {
        this.getNode().setOnMouseReleased(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMouseReleased(mouseEvent);
        });
    }

    private void setOnMouseEntered() {
        this.getNode().setOnMouseEntered(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMouseEntered(mouseEvent);
        });
    }

    private void setOnMouseExited() {
        this.getNode().setOnMouseExited(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMouseExited(mouseEvent);
        });
    }

    private void setOnMouseMoved() {
        this.getNode().setOnMouseMoved(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMouseMoved(mouseEvent);
        });
    }

    private void setOnMouseDragged() {
        this.getNode().setOnMouseDragged(mouseEvent -> {
            if (noteObjectBehavior != null)
                noteObjectBehavior.onMouseDragged(mouseEvent);
        });
    }

    private void loadNewContextMenu(){
        MenuItem copy = new MenuItem("Kopiera");
        MenuItem remove = new MenuItem("Ta bort");
        MenuItem putToFront = new MenuItem("Placera längst fram");
        copy.setOnAction(KeyEvent -> {
            NoteObjectCloner.setCopiedObject(this.getNode());
        });
        remove.setOnAction(actionEvent -> {
            removeThisNode();
        });
        putToFront.setOnAction(actionEvent -> {
            this.getNode().toFront();
        });
        this.getNode().setOnKeyPressed(KeyEvent ->{
            if(KeyEvent.getCode().equals(KeyCode.DELETE)) {
                remove.fire();
            } else if (KeyEvent.getCode().equals(KeyCode.C) && KeyEvent.isControlDown()){
                copy.fire();
            } // else if (KeyEvent.getCode())
        });

        contextMenu.getItems().addAll(initContextMenuItems());
        contextMenu.getItems().addAll(putToFront, copy, remove);
    }


    void removeThisNode(){
        MainPageController.getCurrentNote().removeNoteObject(this.getNode());
    }

    List<MenuItem> initContextMenuItems(){
        List<MenuItem> menuItemList = new ArrayList<>();
        return menuItemList;
    }




}
