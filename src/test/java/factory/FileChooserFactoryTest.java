package factory;

import factory.FileChooserFactory;
import javafx.stage.FileChooser;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-04-26.
 */
public class FileChooserFactoryTest {
    @Test
    public void createFileChooser() throws Exception {
        FileChooser fileChooser = FileChooserFactory.createFileChooser("Test", "*.jpg", "*.png");
        assertTrue(fileChooser.getExtensionFilters().get(1).getDescription().equals("JPG (*.jpg)") &&
                fileChooser.getExtensionFilters().get(2).getDescription().equals("PNG (*.png)"));
    }

}