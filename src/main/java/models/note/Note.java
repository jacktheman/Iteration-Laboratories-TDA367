package models.note;

import models.noteobject.NoteObjectI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class Note implements Serializable {

    private String name;

    private String tags;

    private static Note currentNote;

    private List<NoteObjectI> models;

    public Note(String name) {
        this.name = name;
        this.tags = "";
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
        this.models.remove(model);
        model.remove();
    }

    public List<NoteObjectI> getModels() {
        return models;
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
        currentNote = new Note(note.getName());
        for (NoteObjectI model : note.getModels())
            currentNote.addNoteObject(model);
    }

    public static Note getCurrentNote() {
        return currentNote;
    }
}
