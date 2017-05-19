package models.note;

import models.noteobject.NoteObjectI;
import models.noteobject.TextContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-19.
 */
public class NoteTest {

    private static final String TEST_STRING = "Test";

    private static final NoteObjectI TEST_NOTE_OBJECT = new TextContainer(TEST_STRING, 0, 0);

    private static Note note;

    @BeforeClass
    public static void initNote() {
        note = new Note(TEST_STRING);
    }

    @Test
    public void addNoteObject() throws Exception {
        int nElements = note.getModels().size();
        note.addNoteObject(TEST_NOTE_OBJECT);
        assertTrue(nElements < note.getModels().size());
    }

    @Test
    public void removeNoteObject() throws Exception {
        int nElements = note.getModels().size();
        note.removeNoteObject(TEST_NOTE_OBJECT);
        assertTrue(nElements > note.getModels().size());
    }

    @Test
    public void addTag() throws Exception { //TODO update
        int nTags = note.getTags().split(Pattern.quote(".")).length;
        note.addTag(TEST_STRING);
        assertTrue(note.getTags().contains(TEST_STRING.toLowerCase()));
    }

    @Test
    public void removeTag() throws Exception { //TODO update
        int nTags = note.getTags().split(Pattern.quote(".")).length;
        note.removeTag(TEST_STRING);
        assertFalse(note.getTags().contains(TEST_STRING));
    }

}