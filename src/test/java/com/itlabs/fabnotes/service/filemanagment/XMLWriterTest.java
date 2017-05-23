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
 * Created by aron on 2017-05-23.
 */
public class XMLWriterTest {
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
        File file = XMLWriter.writeToXML(noteSave);
        assertTrue(file.exists());
    }


}