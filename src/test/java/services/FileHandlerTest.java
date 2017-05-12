package services;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexandra on 2017-05-12.
 */
public class FileHandlerTest {

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

}