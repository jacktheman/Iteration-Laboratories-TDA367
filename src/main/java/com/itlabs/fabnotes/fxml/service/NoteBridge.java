package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.note.event.Event;
import com.itlabs.fabnotes.note.model.Note;
import com.itlabs.fabnotes.service.NoteSave;
import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import javafx.scene.Node;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

/**
 * Created by aron on 2017-05-24.
 */
public class NoteBridge {

    public static void createNewNote(List<Node> nodes) {
        Note.setCurrentNote(new Note());
        nodes.clear();
        Note.setCurrentNodes(nodes);
    }

    public static void setNoteName(String name) {
        Note.getCurrentNote().setName(name);
    }

    public static String getNoteName() {
        return Note.getCurrentNote().getName();
    }

    public static boolean addNoteTag(String tag) {
        return Note.getCurrentNote().addTag(tag);
    }

    public static void removeNoteTag(String tag) {
        Note.getCurrentNote().removeTag(tag);
    }

    public static List<String> getNoteTags() {
        return Note.getCurrentNote().getTags();
    }

    public static void saveNote() throws TransformerException, ParserConfigurationException {
        FileHandler.saveNote(new NoteSave(Note.getCurrentNote()));
    }

    public static void undoNoteAction() {
        Event.getEvents().get(Event.getEvents().size() - 1).undo();
    }
}
