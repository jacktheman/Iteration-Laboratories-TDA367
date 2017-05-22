package services;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexandra on 2017-05-12.
 */
public class FileHandlerTest {

    private static final String TEST_STRING = "Test";

    @Test
    public void loadTags() throws Exception {
        List<String> tags = FileHandler.loadTags();
        assertNotNull(tags);
    }

    @Test
    public void addRemoveTags() throws Exception {
        List<String> tags = FileHandler.loadTags();
        List<String> tagsAfterAdd;
        List<String> tagsAfterRemove;
        FileHandler.addTags(TEST_STRING);
        tagsAfterAdd = FileHandler.loadTags();
        FileHandler.removeTagFromTagList(TEST_STRING);
        tagsAfterRemove = FileHandler.loadTags();
        assertTrue(tags.size() == tagsAfterRemove.size() && tags.size() < tagsAfterAdd.size());
    }

    @Test
    public void listNotes() throws Exception {
        File[] files = FileHandler.listNotes();
        for (File file : files)
            assertFalse(file.getName().equals(FileHandler.FILE_TYPE));
    }

}