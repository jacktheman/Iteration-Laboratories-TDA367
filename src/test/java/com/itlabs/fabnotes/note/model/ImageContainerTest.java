package com.itlabs.fabnotes.note.model;

import com.itlabs.fabnotes.note.model.ImageContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by svante on 2017-05-19.
 */
public class ImageContainerTest {

    private final static String TEST_STRING = "Hallå där din lilla sneglare";
    private final static String URL = "file:///home/svante/Documents/TDA367/Iteration-Laboratories-TDA367/Fab64x64.png";
    private final static double TEST_WIDTH = 5;
    private final static double TEST_HEIGHT = 5;
    private final static double TEST_DOUBLE = 5;

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
        imageContainer.setFitWidth(TEST_WIDTH);
        assertTrue(imageContainer.getFitWidth() == TEST_WIDTH);
    }

    @Test
    public void setFitHeight() throws Exception {
        imageContainer.setFitHeight(TEST_HEIGHT);
        assertTrue(imageContainer.getFitHeight() == TEST_HEIGHT);
    }

    @Test
    public void setLayoutX() throws Exception {
        imageContainer.setLayoutX(TEST_DOUBLE);
        assertTrue(imageContainer.getLayoutX() == TEST_DOUBLE);
    }

    @Test
    public void setLayoutY() throws Exception {
        imageContainer.setLayoutY(TEST_DOUBLE);
        assertTrue(imageContainer.getLayoutY() == TEST_DOUBLE);
    }

    @Test
    public void duplicate() throws Exception {
        ImageContainer imageURLContainer = imageContainer.duplicate();
        assertTrue(imageURLContainer.getLayoutY() == imageContainer.getLayoutY() &&
                imageURLContainer.getLayoutX() == imageContainer.getLayoutX() &&
                imageURLContainer.getFitHeight() == imageContainer.getFitHeight() &&
                imageURLContainer.getFitWidth() == imageContainer.getFitWidth() &&
                imageURLContainer.getQuota() == imageContainer.getQuota() &&
                imageURLContainer.getURL().equals(imageContainer.getURL()));
    }

}