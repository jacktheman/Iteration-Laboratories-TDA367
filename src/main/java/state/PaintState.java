package state;

import controllers.noteobject.PaintingContainerController;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.note.Note;
import models.noteobject.NoteObjectI;

import java.net.MalformedURLException;

/**
 * Created by jackflurry on 2017-04-27.
 */
public class PaintState extends NoteState {

    private static PaintState SINGLETON = new PaintState();

    private PaintState(){}

    public static PaintState getInstance(){
        return SINGLETON;
    }

    @Override
    public void getOnMousePressed(AnchorPane notePane, MouseEvent event) throws MalformedURLException {
        super.getOnMousePressed(notePane, event);
        if (!super.pressedFocusOwner(notePane, event)) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                Note.getCurrentNote().addNoteObject((new PaintingContainerController(event)).getModel());
            }
        }
    }

}
