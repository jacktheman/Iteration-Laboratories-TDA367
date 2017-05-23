package com.itlabs.fabnotes.note.event;

import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;

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
