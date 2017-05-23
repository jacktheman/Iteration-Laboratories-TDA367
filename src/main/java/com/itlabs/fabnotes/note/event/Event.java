package com.itlabs.fabnotes.note.event;

import com.itlabs.fabnotes.noteobject.model.NoteObjectI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-08.
 */
public abstract class Event<T extends NoteObjectI> {

    private T model;
    private static List<Event> events = new ArrayList<>();

    private static final int MAX_NUMBER_OF_EVENTS = 20;

    public Event(T model){
        this.model = model;
    }

    public T getModel(){
        return model;
    }

    public static List<Event> getEvents(){
        return events;
    }

    public static void addEvent(Event event){
        if(events.size() == MAX_NUMBER_OF_EVENTS){
            events.remove(0);
        }
        events.add(event);

    }

    public abstract void undo();

}
