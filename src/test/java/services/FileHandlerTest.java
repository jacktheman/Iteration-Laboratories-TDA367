package services;

import models.note.Note;
import org.junit.Test;
import views.noteobject.TableView;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandlerTest {
    @Test
    public void checkFileName1() throws Exception {
        String name = FileHandler.checkFileName("Test");
        assertTrue(name.equals("Test"));
    }

    @Test
    public void checkFileName2() throws Exception {
        String name = FileHandler.checkFileName("test");
        assertTrue(name.equals("test_1"));
    }

    @Test
    public void saveNote() {
        Note note = new Note("TestNote");
        TableView tableView = new TableView();
        note.addNoteObject(tableView);
        try {
            File file = FileHandler.saveNote(note);
            if (file != null)
                assertTrue(file.exists());
            else
                fail("Did not save file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadNote() {
        File file = new File(FileHandler.FILE_PATH + "TestNote" + FileHandler.FILE_TYPE);
        try {
            Note note = FileHandler.loadNote(file);
            if (note != null)
                assertTrue(note.getNodes().get(0) instanceof TableView);
            else
                fail("Data lost");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}