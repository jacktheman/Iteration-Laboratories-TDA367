package services;

import controllers.fxml.MainPageController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import models.note.Note;

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

    public static MenuItem removeItem(Node view){
        MenuItem remove = new MenuItem("Ta bort");
        remove.setOnAction(actionEvent -> {
            //Note.getCurrentNote().removeNoteObject(view);
        });
        return remove;
    }

    public static MenuItem copyItem(Node view){
        MenuItem copy = new MenuItem("Kopiera");
        copy.setOnAction(KeyEvent -> {
            NoteObjectCloner.setCopiedObject(view);
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
