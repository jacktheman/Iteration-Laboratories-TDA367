package models.note;

import controllers.noteobject.NoteObjectControllerI;
import javafx.scene.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svante on 2017-04-07.
 */
public class Note implements Serializable {

    private static final String FILE_PATH = System.getProperty("user.home") + File.separator + ".fabNotes"
            + File.separator;

    private String name;

    private List<String> tags;

    private List<NoteObjectControllerI> controllers;

    public Note(String name) {
        this.name = name;
        this.tags = new ArrayList<>();
        this.controllers = new ArrayList<>();
    }

    public Note() {
        this("nameless");
    }

    public void addNoteObjectController(NoteObjectControllerI controller) {
        this.controllers.add(controller);
    }

    public void removeNoteObjectController(NoteObjectControllerI controller) {
        this.controllers.remove(controller);
    }

    public List<Node> getNodes() {
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

    public void writeToFile() {
        File file = new File(FILE_PATH + name + ".fab");
        if (file.exists()) {
            name = name + "_N";
        }
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH + name + ".fab");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
