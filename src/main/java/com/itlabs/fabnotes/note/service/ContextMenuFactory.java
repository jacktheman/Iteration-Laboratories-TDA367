package com.itlabs.fabnotes.note.service;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.NoteObjectI;

/**
 * Created by svante on 2017-05-10.
 */
public class ContextMenuFactory {

    private static final String REMOVE = "Ta bort";
    private static final String COPY = "Kopiera";
    private static final String MOVE_TO_FRONT = "Flytta längst fram";

    private static NoteObjectI copiedObject;

    private ContextMenuFactory(){}

    public static ContextMenu createContextMenu(MenuItem ... menuItems){

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(menuItems);

        return contextMenu;
    }

    public static MenuItem removeItem(NoteObjectI model){
        MenuItem remove = new MenuItem(REMOVE);
        remove.setOnAction(actionEvent -> {
            Note.getCurrentNote().removeNoteObject(model);
        });
        return remove;
    }

    public static MenuItem copyItem(NoteObjectI model){
        MenuItem copy = new MenuItem(COPY);
        copy.setOnAction(KeyEvent -> {
            copiedObject = model.duplicate();
        });
        return copy;
    }

    public static MenuItem putToFrontItem(Node view){
        MenuItem putToFront = new MenuItem(MOVE_TO_FRONT);
        putToFront.setOnAction(actionEvent -> {
            view.toFront();
        });
        return putToFront;
    }

    public static NoteObjectI getCopiedObject() {
        return copiedObject;
    }
}
