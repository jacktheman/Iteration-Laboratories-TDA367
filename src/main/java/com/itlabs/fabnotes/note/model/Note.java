package com.itlabs.fabnotes.note.model;

import javafx.scene.Node;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class Note implements Serializable {

    private String name;

    private List<String> tags;

    private List<NoteObjectI> models;

    private static Note currentNote;

    private static List<Node> currentNodes;

    public Note(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
        this.models = new ArrayList<>();
    }

    public Note() {
        this("");
    }

    public void addNoteObject(NoteObjectI model) {
        if (!this.models.contains(model)) {
            this.models.add(model);
            if (this == currentNote)
                model.add();
        }
    }

    public void removeNoteObject(NoteObjectI model) {
        //Event.addEvent(new RemoveNoteEvent(model));
        this.models.remove(model);
        if (this == currentNote)
            model.remove();
    }

    public List<NoteObjectI> getModels() {
        return models;
    }

    public String getName() {
        return name;
    }

    public List<String> getTags(){
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(List<String> tags) { //istället för String som inargument
        this.tags = tags;
    }

    public boolean addTag(String tag) {
        if (!tags.contains(tag.toLowerCase())) {
            tags.add(tag.toLowerCase());
            return true;
        }
        return false;
    }

    public void removeTag(String tag) {
        tags.remove(tag.toLowerCase());
    }

    public static void setCurrentNote(Note note) {
        currentNote = new Note(note.getName());
        currentNote.setTags(note.getTags());
        for (NoteObjectI model : note.getModels())
            currentNote.addNoteObject(model);
    }

    public static Note getCurrentNote() {
        return currentNote;
    }

    public static void setCurrentNodes(List<Node> currentNodes) {
        Note.currentNodes = currentNodes;
    }

    public static List<Node> getCurrentNodes() {
        return currentNodes;
    }
}
