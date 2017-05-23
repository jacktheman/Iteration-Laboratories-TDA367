package com.itlabs.fabnotes.service.filemanagment;

import com.itlabs.fabnotes.noteobject.model.NoteObjectI;
import com.itlabs.fabnotes.noteobject.model.TextContainer;
import com.itlabs.fabnotes.service.NoteSave;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-22.
 */
public class XMLReaderTest {
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
    public void readXMLToNote() throws Exception {
        File file = new File(FileHandler.FILE_PATH + TEST_STRING + FileHandler.FILE_TYPE);
        NoteSave noteSave = XMLReader.readXMLToNote(file);
        assertTrue(noteSave.getName().equals(TEST_STRING) && noteSave.getTags().size() == 1 &&
        noteSave.getTags().get(0).equals(TEST_STRING) && noteSave.getModels().size() == 1 &&
        noteSave.getModels().get(0) instanceof TextContainer);
    }

}