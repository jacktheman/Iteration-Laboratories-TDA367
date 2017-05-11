package utilities.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackflurry on 2017-05-08.
 */
public abstract class Event {

    private Object object;
    private static List<Event> events = new ArrayList<>();

    public Event(Object object){
        this.object = object;
    }

    public Object getObject(){
        return object;
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
