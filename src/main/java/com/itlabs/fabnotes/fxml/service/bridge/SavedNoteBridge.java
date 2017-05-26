package com.itlabs.fabnotes.fxml.service.bridge;

import com.itlabs.fabnotes.note.save.NoteSave;
import com.itlabs.fabnotes.note.save.xml.XMLReader;
import com.itlabs.fabnotes.note.save.xml.XMLWriter;
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

    public void save(String filePath, String fileName) throws TransformerException, ParserConfigurationException {
        if (noteSave.getModels().size() > 0 && !noteSave.getName().equals(""))
            XMLWriter.writeToXML(noteSave, filePath, fileName);
    }

    public static SavedNoteBridge loadSavedNote(File file) throws ClassNotFoundException, ParserConfigurationException, SAXException, IOException {
        if (file.exists())
            return new SavedNoteBridge(XMLReader.readXMLToNote(file));
        return null;
    }

}
