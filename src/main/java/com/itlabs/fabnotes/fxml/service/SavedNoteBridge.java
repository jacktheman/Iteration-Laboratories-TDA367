package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.service.NoteSave;
import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by aron on 2017-05-24.
 */
public class SavedNoteBridge {

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

}
