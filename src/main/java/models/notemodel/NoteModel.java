package models.notemodel;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.NoteObjectObserverI;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class NoteModel implements NoteObjectObserverI {

    private List<Node> nodes;
    private String name;
    private List<String> tags;
    private List<NoteObjectControllerI> controllers;

    public NoteModel(){
        nodes = new ArrayList<>();
        name = "";
        tags = new ArrayList<>();
        controllers = new ArrayList<>();

    }

    public void addNoteObjectController(NoteObjectControllerI controller){
        controller.addListener(this);
        this.controllers.add(controller);
        this.nodes.add(controller.getNode());
    }

    public void removeNoteObjectController(NoteObjectControllerI controller){
        controller.removeListener(this);
        this.nodes.remove(controller.getNode());
        this.controllers.remove(controller);
    }

    public List<Node> getNodes(){
        return this.nodes;
    }

    public void save(){}

    @Override
    public void fireChange(NoteObjectControllerI controller) {
        removeNoteObjectController(controller);
    }



    // #TODO Fixa get/set för alla variabler
}
