package com.itlabs.fabnotes.service.filemanagment;

import com.itlabs.fabnotes.noteobject.model.TextContainer;
import com.itlabs.fabnotes.service.NoteSave;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-22.
 */
public class XMLReaderTest {
    private static final String TEST_STRING = "Test";

    @Test
    public void readXMLToNote() throws Exception {
        File file = new File(FileHandler.FILE_PATH + TEST_STRING + FileHandler.FILE_TYPE);
        NoteSave noteSave = XMLReader.readXMLToNote(file);
        assertTrue(noteSave.getName().equals(TEST_STRING) && noteSave.getTags().size() == 1 &&
        noteSave.getTags().get(0).equals(TEST_STRING) && noteSave.getModels().size() == 1 &&
        noteSave.getModels().get(0) instanceof TextContainer);
    }

}