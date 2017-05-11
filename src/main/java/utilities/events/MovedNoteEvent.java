package utilities.events;

import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.Node;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class MovedNoteEvent extends Event {

    private final double x;
    private final double y;

    public MovedNoteEvent(Object object, double x, double y) {
        super(object);
        this.x = x;
        this.y = y;
    }

    @Override
    public void undo() {
        ((Node) super.getObject()).setLayoutX(x);
        ((Node) super.getObject()).setLayoutY(y);
        Event.getEvents().remove(this);
    }
}
