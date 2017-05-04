package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import utilities.ObservableI;
import utilities.ObserverI;

/**
 * Created by svante on 2017-04-06.
 */
public interface NoteObjectControllerI extends ObservableI {
    Node getNode();
}
