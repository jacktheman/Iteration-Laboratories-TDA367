package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import org.junit.platform.commons.annotation.Testable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController implements NoteObjectControllerI {

    private static int hash = 0;

    private Node view;

    private List<NoteObjectObserverI> listeners;

    NoteObjectController(Node view){
        this.view = view;
        this.listeners = new ArrayList<>();
    }

    private void drag(){

    }

    private void drop(){

    }

    private void onRightClick(){

    }

    void notifyListeners(){
        for(int i = 0; i < listeners.size(); i++){
            listeners.get(i).fireChange(this);
        }
    }

    @Override
    public Node getNode() {
        return view;
    }

    @Override
    public void addListener(NoteObjectObserverI noteObjectObserver) {
        listeners.add(noteObjectObserver);
    }

    @Override
    public void removeListener(NoteObjectObserverI noteObjectObserver) {
        listeners.remove(noteObjectObserver);
    }


}
