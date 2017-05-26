package com.itlabs.fabnotes.note.event;

import com.itlabs.fabnotes.note.model.note.Note;
import com.itlabs.fabnotes.note.model.noteobject.noteobject.NoteObjectI;

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
        getEvents().remove(this);
    }
}
