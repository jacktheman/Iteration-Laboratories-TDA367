package models.note;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-04-14.
 */
public class NoteModelTest {

    @Test
    public void addTag() throws Exception {
        NoteModel noteModel = new NoteModel("Test");
        List<String> tags = noteModel.getTags();

        int nTagsBefore = tags.size();

        noteModel.addTag("test");
        tags = noteModel.getTags();

        int nTagsAfter = tags.size();
        assertTrue(nTagsBefore < nTagsAfter);
    }

    @Test
    public void removeTag() throws Exception {
        NoteModel noteModel = new NoteModel("Test");
        noteModel.addTag("test");
        List<String> tags = noteModel.getTags();

        int nTagsBefore = tags.size();

        noteModel.removeTag("test");
        tags = noteModel.getTags();

        int nTagsAfter = tags.size();
        assertTrue(nTagsBefore > nTagsAfter);
    }

}