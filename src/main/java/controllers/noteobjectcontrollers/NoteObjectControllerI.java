package controllers.noteobjectcontrollers;

import javafx.scene.Node;

/**
 * Created by svante on 2017-04-06.
 */
public interface NoteObjectControllerI {

    public Node getNode();

    public void addListener(NoteObjectObserverI noteObjectObserver);

    public void removeListener(NoteObjectObserverI noteObjectObserver);

}
