package utilities.events;

import controllers.fxml.MainPageController;
import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.Node;
import models.note.Note;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class AddNoteEvent extends Event {

    public AddNoteEvent(Object object){
        super(object);
    }

    @Override
    public void undo() {
        Note.getCurrentNote().removeNoteObject((Node)super.getObject());
        Event.getEvents().remove(this);
    }
}
