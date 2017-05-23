package state;

import controllers.noteobject.TableController;
import controllers.noteobject.TextContainerController;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import models.note.Note;
import models.noteobject.NoteObjectI;

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
            } else if (event.getButton().equals(MouseButton.MIDDLE)) {
                model = (new TableController(event.getX(),event.getY(), 3, 3)).getModel();
            }
            if (model != null)
                Note.getCurrentNote().addNoteObject(model);
        }
    }

}
