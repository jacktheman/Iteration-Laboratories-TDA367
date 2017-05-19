package models.noteobject;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by svante on 2017-05-19.
 */
public class ImageContainerTest {

    private final static String TEST_STRING = "Hallå där din lilla sneglare";
    private final static String URL = "file:///home/svante/Documents/TDA367/Iteration-Laboratories-TDA367/Fab64x64.png";
    private final static double TEST_WIDTH = 5;
    private final static double TEST_HEIGHT = 5;

    private static ImageContainer imageContainer;

    @BeforeClass
    public static void initTest() {

        imageContainer = new ImageContainer(URL, TEST_WIDTH, TEST_HEIGHT);
    }

    @Test
    public void setURL() throws Exception {
        imageContainer.setURL(URL);
        assertTrue(imageContainer.getURL().equals(URL));
    }

    @Test
    public void setFitWidth() throws Exception {
    }

    @Test
    public void setFitHeight() throws Exception {
    }

    @Test
    public void setLayoutX() throws Exception {
    }

    @Test
    public void setLayoutY() throws Exception {
    }
    

}