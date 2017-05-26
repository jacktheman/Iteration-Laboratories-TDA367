package com.itlabs.fabnotes.note.save;

import com.itlabs.fabnotes.note.model.ImageContainer;
import com.itlabs.fabnotes.note.model.NoteObjectI;
import com.itlabs.fabnotes.note.model.TextContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by svante on 2017-05-26.
 */
public class NoteSaveTest {

    private static NoteSave noteSave;
    private static final String TEST_STRING = "test";
    private static final double TEST_DOUBLE = 5;

    @BeforeClass
    public static void initTest(){
        List<NoteObjectI> MODELS = new ArrayList<>();
        List<String> TAGS = new ArrayList<>();
        NoteObjectI NOTE_OBJECT = new TextContainer(TEST_STRING, TEST_DOUBLE, TEST_DOUBLE);
        MODELS.add(NOTE_OBJECT);
        TAGS.add(TEST_STRING);
        NoteSaveTest.noteSave = new NoteSave(TEST_STRING, TAGS, MODELS);
    }

    @Test
    public void getName() throws Exception {
        assertTrue(noteSave.getName().equals(TEST_STRING));
    }

    @Test
    public void getTags() throws Exception {
        assertTrue(noteSave.getTags().get(0).equals(TEST_STRING));
    }

    @Test
    public void getModels() throws Exception {
        assertNotNull(noteSave.getModels().get(0));
    }

}