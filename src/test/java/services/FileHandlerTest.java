package services;

import models.note.Note;
import models.noteobject.TextContainer;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexandra on 2017-05-12.
 */
public class FileHandlerTest {

    @Test
    public void saveNote() throws Exception {
        Note note = new Note("TestNote");
        note.addNoteObject(new TextContainer("test", 1, 1));
        assertNotNull(FileHandler.saveNote(new NoteSave(note)));
    }

    @Test
    public void loadNote() throws Exception {
        assertNotNull(FileHandler.loadNote(new File(FileHandler.FILE_PATH + "TestNote" + FileHandler.FILE_TYPE)));
    }

    @Test
    public void loadTags() throws Exception {
        List<String> tags = FileHandler.loadTags();
        System.out.println (tags);
        assertTrue(tags.size() > 0);
    }

    @Test
    public void addTags() throws Exception {
        List<String> tags = FileHandler.loadTags();
        FileHandler.addTags("åsna");
        assertTrue(tags.size() < FileHandler.loadTags().size());
    }

    @Test
    public void listNotes() throws Exception {
        File[] files = FileHandler.listNotes();
        for (File file : files)
            assertFalse(file.getName().equals(FileHandler.FILE_TYPE));
    }

}