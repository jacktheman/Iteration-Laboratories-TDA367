package com.itlabs.fabnotes.fxml.state;

import com.itlabs.fabnotes.note.controller.TextContainerController;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.note.model.NoteObjectI;

import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */


public class WriteState extends NoteState {

    private static WriteState SINGLETON = new WriteState();

    private WriteState() {}

    public static WriteState getInstance() {
        return SINGLETON;
    }

    @Override
    public void getOnMouseReleased(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        if (!super.pressedFocusOwner(notePane, event)) {
            NoteObjectI model = null;
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                model = (new TextContainerController("", event.getX(), event.getY())).getModel();
            }
            if (model != null)
                Note.getCurrentNote().addNoteObject(model);
        }
    }

}
