package com.itlabs.fabnotes.noteobject.event;

import com.itlabs.fabnotes.note.event.*;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.noteobject.model.PaintingContainer;

/**
 * Created by jackflurry on 2017-05-23.
 */
public class NoteEventHandler {

    private static NoteEventHandler SINGLETON = new NoteEventHandler();

    private NoteEventHandler(){}

    public void createAddEvent(NoteObjectI model){
        Event.addEvent(new AddNoteEvent(model));
    }

    public void createRemoveEvent(NoteObjectI model){
        if(!(Event.getEvents().get(Event.getEvents().size()-1).getModel() == model)) {
            Event.addEvent(new RemoveNoteEvent(model));
        }
    }

    public void createMoveEvent(NoteObjectI model, double x, double y){
        Event.addEvent(new MovedNoteEvent(model, x, y));
    }

    public void createPaintingEvent(PaintingContainer model){
        Event.addEvent(new PaintingEvent(model));
    }

    public static NoteEventHandler getInstance() {
        return SINGLETON;
    }


}
