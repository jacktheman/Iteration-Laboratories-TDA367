package com.itlabs.fabnotes.note.event;

import com.itlabs.fabnotes.noteobject.model.NoteObjectI;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class MovedNoteEvent extends Event {

    private final double x;
    private final double y;

    public MovedNoteEvent(NoteObjectI model, double x, double y) {
        super(model);
        this.x = x;
        this.y = y;
    }

    @Override
    public void undo() {
        super.getModel().setLayoutX(x);
        super.getModel().setLayoutY(y);
        Event.getEvents().remove(this);
    }
}
