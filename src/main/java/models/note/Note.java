package models.note;

import javafx.scene.Node;
import utilities.ObservableI;
import utilities.ObserverI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class Note implements ObservableI<Node> {

    private String name;

    private List<String> tags;

    private List<Node> nodes;

    private List<ObserverI<Node>> listeners;

    private static Note currentNote;

    public Note(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
        this.nodes = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public Note() {
        this("nameless");
    }

    public void addNoteObject(Node node) {
        this.nodes.add(node);
        notifyListeners(node);
    }

    public void removeNoteObject(Node node) {
        this.nodes.remove(node);
        notifyListeners(node);
    }

    public List<Node> getNodes() {
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

    private void notifyListeners(Node node) {
        for (ObserverI<Node> listener : listeners)
            listener.fireChange(node);
    }

    @Override
    public void addListener(ObserverI<Node> observer) {
        this.listeners.add(observer);
    }

    @Override
    public void removeListener(ObserverI<Node> observer) {
        this.listeners.remove(observer);
    }

    public static void setCurrentNote(Note note) {
        currentNote = note;
    }

    public static Note getCurrentNote() {
        return currentNote;
    }
}
