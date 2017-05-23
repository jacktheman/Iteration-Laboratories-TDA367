package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.fxml.service.FileHandler;
//import com.itlabs.fabnotes.fxml.service.XMLHandler;
import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.noteobject.model.TextContainer;
import org.junit.BeforeClass;
import org.junit.Test;
import com.itlabs.fabnotes.save.NoteSave;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-22.
 */
public class XMLHandlerTest {
    private static final String TEST_STRING = "Test";
    private static final int TEST_NUMBER = 0;

    private static List<String> tags;
    private static List<NoteObjectI> models;

    @BeforeClass
    public static void initTest() {
        tags = new ArrayList<>();
        models = new ArrayList<>();
        tags.add(TEST_STRING);
        models.add(new TextContainer(TEST_STRING, TEST_NUMBER, TEST_NUMBER));
    }

    @Test
    public void writeToXML() throws Exception {
        NoteSave noteSave = new NoteSave(TEST_STRING, tags, models);
        File file = XMLHandler.writeToXML(noteSave);
        assertTrue(file.exists());
    }

    @Test
    public void readXMLToNote() throws Exception {
        File file = new File(FileHandler.FILE_PATH + TEST_STRING + FileHandler.FILE_TYPE);
        NoteSave noteSave = XMLHandler.readXMLToNote(file);
        assertTrue(noteSave.getName().equals(TEST_STRING) && noteSave.getTags().size() == 1 &&
        noteSave.getTags().get(0).equals(TEST_STRING) && noteSave.getModels().size() == 1 &&
        noteSave.getModels().get(0) instanceof TextContainer);
    }

}