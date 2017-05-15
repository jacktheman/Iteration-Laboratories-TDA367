package services;

import controllers.fxml.MainPageController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import models.note.Note;
import models.noteobject.NoteObjectI;

/**
 * Created by svante on 2017-05-10.
 */
public class ContextMenuFactory {

    private ContextMenuFactory(){}

    public static ContextMenu createContextMenu(MenuItem ... menuItems){

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(menuItems);

        return contextMenu;
    }

    public static MenuItem removeItem(NoteObjectI model){
        MenuItem remove = new MenuItem("Ta bort");
        remove.setOnAction(actionEvent -> {
            Note.getCurrentNote().removeNoteObject(model);
        });
        return remove;
    }

    public static MenuItem copyItem(NoteObjectI model){
        MenuItem copy = new MenuItem("Kopiera");
        copy.setOnAction(KeyEvent -> {
            NoteObjectCloner.setCopiedObject(model);
            System.out.println("Kopierade något");
        });
        return copy;
    }

    public static MenuItem putToFrontItem(Node view){
        MenuItem putToFront = new MenuItem("Flytta längst fram");
        putToFront.setOnAction(actionEvent -> {
            view.toFront();
        });
        return putToFront;
    }

}
