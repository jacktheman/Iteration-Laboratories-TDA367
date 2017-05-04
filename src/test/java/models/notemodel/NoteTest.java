package models.notemodel;

import models.note.Note;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-04-14.
 */
public class NoteTest {

    @Test
    public void addTag() throws Exception {
        Note Note = new Note("Test");
        List<String> tags = Note.getTags();

        int nTagsBefore = tags.size();

        Note.addTag("test");
        tags = Note.getTags();

        int nTagsAfter = tags.size();
        assertTrue(nTagsBefore < nTagsAfter);
    }

    @Test
    public void removeTag() throws Exception {
        Note Note = new Note("Test");
        Note.addTag("test");
        List<String> tags = Note.getTags();

        int nTagsBefore = tags.size();

        Note.removeTag("test");
        tags = Note.getTags();

        int nTagsAfter = tags.size();
        assertTrue(nTagsBefore > nTagsAfter);
    }

}