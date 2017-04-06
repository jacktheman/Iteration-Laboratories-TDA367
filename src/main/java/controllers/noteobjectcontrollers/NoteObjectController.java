package controllers.noteobjectcontrollers;

import javafx.scene.Node;
import models.NoteObjectI;

/**
 * Created by svante on 2017-04-06.
 */
abstract class NoteObjectController implements NoteObjectControllerI {

    private Node view;
    private NoteObjectI model;

    NoteObjectController(Node view){
        this.view = view;
    }


    private void drag(){

    }

    private void drop(){

    }

    private void onRightClick(){

    }

    @Override
    public Node getNode() {
        return view;
    }

    abstract void onFocus();


}
