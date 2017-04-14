package controllers.noteobjectcontrollers;

import javafx.scene.Node;

/**
 * Created by svante on 2017-04-06.
 */
public interface NoteObjectControllerI {

    Node getNode();

    void addListener(NoteObjectObserverI noteObjectObserver);

    void removeListener(NoteObjectObserverI noteObjectObserver);

}
