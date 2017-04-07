package controllers.noteobjectcontrollers;

import javafx.scene.Node;

/**
 * Created by svante on 2017-04-07.
 */
public interface NoteObjectObserverI {

    public void fireChange(NoteObjectControllerI subject);
}
