package factory;

import javafx.stage.FileChooser;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-04-26.
 */
public class FileChooserFactoryTest {

    private static final String TEST_STRING = "Test";
    private static final String[] EXTENSIONS = {"*.jpg", "*.png"};
    private static final String [] EXTENSION_DESCRIPTIONS = {"JPG (*.jpg)", "PNG (*.png)"};

    @Test
    public void createFileChooser() throws Exception {
        FileChooser fileChooser = FileChooserFactory.createFileChooser(TEST_STRING, EXTENSIONS);
        assertTrue(fileChooser.getExtensionFilters().get(1).getDescription().equals(EXTENSION_DESCRIPTIONS[0]) &&
                fileChooser.getExtensionFilters().get(2).getDescription().equals(EXTENSION_DESCRIPTIONS[1]));
    }

}