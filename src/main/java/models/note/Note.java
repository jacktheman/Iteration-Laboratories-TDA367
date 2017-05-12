package models.note;

import javafx.scene.Node;
import models.noteobject.NoteObjectI;
import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class Note {

    private String name;

    private String tags;

    private List<Node> nodes;

    private static Note currentNote;

    private List<NoteObjectI> models;

    public Note(String name) {
        this.name = name;
        this.tags = "";
        this.nodes = new ArrayList<>();
        this.models = new ArrayList<>();
    }

    public Note() {
        this("");
    }

    public void addNoteObject(NoteObjectI model) {
        if (!this.models.contains(model)) {
            this.models.add(model);
            model.add();
        }
    }

    public void removeNoteObject(NoteObjectI model) {
        this.nodes.remove(model);
        model.remove();
    }

    public List<NoteObjectI> getModels() {
        return models;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean addTag(String tag) {
        if (!tags.contains(tag.toLowerCase())) {
            tags += "." + tag.toLowerCase();
            return true;
        }
        return false;
    }

    public void removeTag(String tag) {
        String temp = tags.replace(tag.toLowerCase(), "");
        tags = temp.replace("..", ".");
    }

    public static void setCurrentNote(Note note) {
        currentNote = note;
    }

    public static Note getCurrentNote() {
        return currentNote;
    }
}
