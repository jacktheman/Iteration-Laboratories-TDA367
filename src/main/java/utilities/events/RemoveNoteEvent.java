package utilities.events;

import controllers.fxml.MainPageController;
import javafx.scene.Node;
import models.note.Note;
import models.noteobject.NoteObjectI;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class RemoveNoteEvent extends Event {


    public RemoveNoteEvent(NoteObjectI model) {
        super(model);
    }

    @Override
    public void undo() {
        Note.getCurrentNote().addNoteObject(super.getModel());
        Event.getEvents().remove(this);
    }
}
