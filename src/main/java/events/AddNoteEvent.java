package events;

import models.note.Note;
import models.noteobject.NoteObjectI;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class AddNoteEvent extends Event {

    public AddNoteEvent(NoteObjectI model){
        super(model);
    }

    @Override
    public void undo() {
        Note.getCurrentNote().removeNoteObject(super.getModel());
        Event.getEvents().remove(this);
    }
}
