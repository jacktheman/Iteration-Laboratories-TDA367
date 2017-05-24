package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.service.NoteSave;
import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 2017-05-24.
 */
public class SavedNoteBridge {

    public static final String FILE_PATH = FileHandler.FILE_PATH;
    public static final String FILE_TYPE = FileHandler.FILE_TYPE;

    private NoteSave noteSave;

    private SavedNoteBridge(NoteSave noteSave) {
        this.noteSave = noteSave;
    }

    public String getName() {
        return noteSave.getName();
    }

    public List<String> getTags() {
        return noteSave.getTags();
    }

    public boolean initializeNoteObjects() {
        return noteSave.loadControllers();
    }

    public void save() throws TransformerException, ParserConfigurationException {
        FileHandler.saveNote(noteSave);
    }

    public static SavedNoteBridge loadSavedNote(File file) throws ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        return new SavedNoteBridge(FileHandler.loadNote(file));
    }

    public static List<String> loadTagsFromTagList() throws IOException {
        return FileHandler.loadTags();
    }

    public static void addTagToTagsList(String... tags) throws IOException {
        FileHandler.addTags(tags);
    }

    public static void removeTagsFromTagList(String tag) throws IOException {
        FileHandler.removeTagFromTagList(tag);
    }

    public static List<File> getNotesWithTag(String tag) throws ParserConfigurationException, SAXException, IOException {
        return FileHandler.tagList(tag);
    }

    public static List<File> listNotes() {
        List<File> files = new ArrayList<>();
        files.addAll(Arrays.asList(FileHandler.listNotes()));
        return files;
    }

    public static List<File> searchNotes(String word) throws ParserConfigurationException, SAXException, IOException {
        return FileHandler.searchList(word);
    }

}
