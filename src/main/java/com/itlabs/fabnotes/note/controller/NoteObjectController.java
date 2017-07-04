package com.itlabs.fabnotes.note.controller;

import com.itlabs.fabnotes.note.service.NoteEventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.NoteObjectI;
import com.itlabs.fabnotes.note.service.ContextMenuFactory;
import com.itlabs.fabnotes.note.behavior.NoteObjectBehaviorI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController<T1 extends Node, T2 extends NoteObjectI> implements NoteObjectControllerI {

    private T1 view;

    private T2 model;

    private NoteObjectBehaviorI noteObjectBehavior;

    private ContextMenu contextMenu;

    NoteObjectController(T1 view, T2 model) {
        this.view = view;
        this.model = model;
        this.contextMenu = new ContextMenu();
        NoteEventHandler.getInstance().createAddEvent(model);
        loadNewContextMenu();
        setOnMousePressed();
        setOnMouseReleased();
        setOnMouseEntered();
        setOnMouseExited();
        setOnMouseMoved();
        setOnMouseDragged();
    }


    NoteObjectBehaviorI getBehavior() {
        return this.noteObjectBehavior;
    }

    void setBehavior(NoteObjectBehaviorI noteObjectBehavior) {
        this.noteObjectBehavior = noteObjectBehavior;
    }

    private void setOnMousePressed() {
        this.getNode().setOnMousePressed(mouseEvent -> {
            this.getNode().requestFocus();
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
        MenuItem copy = ContextMenuFactory.copyItem(this.getModel());
        MenuItem remove = ContextMenuFactory.removeItem(this.getModel());
        MenuItem putToFront = ContextMenuFactory.putToFrontItem(this.getNode());
        this.getNode().setOnKeyPressed(KeyEvent ->{
            if(KeyEvent.getCode().equals(KeyCode.DELETE)) {
                remove.fire();
            } else if (KeyEvent.getCode().equals(KeyCode.C) && KeyEvent.isControlDown()){
                copy.fire();
            }
        });

        contextMenu = ContextMenuFactory.createContextMenu(copy,remove,putToFront);
    }

    public ContextMenu getContextMenu () {
        return contextMenu;
    }

    @Override
    public T1 getNode() {
        return this.view;
    }

    @Override
    public T2 getModel() {
        return this.model;
    }
}
