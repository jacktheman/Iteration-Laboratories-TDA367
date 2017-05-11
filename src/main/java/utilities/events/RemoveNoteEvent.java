package utilities.events;

import controllers.fxml.MainPageController;
import javafx.scene.Node;
import models.note.Note;

/**
 * Created by jackflurry on 2017-05-08.
 */
public class RemoveNoteEvent extends Event {


    public RemoveNoteEvent(Object object) {
        super(object);
    }

    @Override
    public void undo() {
        Note.getCurrentNote().addNoteObject((Node)super.getObject());
        Event.getEvents().remove(this);
    }
}
