package models.notemodel;

import controllers.noteobjectcontrollers.NoteObjectControllerI;
import controllers.noteobjectcontrollers.NoteObjectObserverI;
import javafx.scene.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class NoteModel implements NoteObjectObserverI {

    private String name;

    private List<String> tags;

    private List<NoteObjectControllerI> controllers;

    public NoteModel(String name){
        this.name = name;
        this.tags = new ArrayList<>();
        this.controllers = new ArrayList<>();
    }

    public NoteModel() {
        this("nameless");
    }

    public void addNoteObjectController(NoteObjectControllerI controller){
        controller.addListener(this);
        this.controllers.add(controller);
    }

    public void removeNoteObjectController(NoteObjectControllerI controller){
        controller.removeListener(this);
        this.controllers.remove(controller);
    }

    public List<Node> getNodes(){
        List<Node> nodes = new ArrayList<>();
        for (NoteObjectControllerI controller : controllers)
            nodes.add(controller.getNode());
        return nodes;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        tags.addAll(this.tags);
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTag(String tag) {
        if (!tags.contains(tag.toLowerCase()))
            tags.add(tag.toLowerCase());
    }

    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    public void save() throws IOException {
    }

    @Override
    public void fireChange(NoteObjectControllerI controller) {
        removeNoteObjectController(controller);
    }
}
