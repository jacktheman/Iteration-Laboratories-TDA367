package utilities.events;

import models.noteobject.NoteObjectI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-08.
 */
public abstract class Event<T extends NoteObjectI> {

    private T model;
    private static List<Event> events = new ArrayList<>();

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
        events.add(event);
        System.out.println(events.toString());
    }

    public abstract void undo();

}
