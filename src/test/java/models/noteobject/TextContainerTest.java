package models.noteobject;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-19.
 */
public class TextContainerTest {

    private static final String DEFAULT_STRING = "";
    private static final int DEFAILT_NUMBER = 0;
    private static final String TEST_STRING = "Test";
    private static final int TEST_NUMBER = 10;

    private static TextContainer textContainer;

    @BeforeClass
    public static void initTest() {
        textContainer = new TextContainer(DEFAULT_STRING, DEFAILT_NUMBER, DEFAILT_NUMBER);
    }

    @Test
    public void setLayoutX() throws Exception {
        textContainer.setLayoutX(TEST_NUMBER);
        assertTrue(textContainer.getLayoutX() == TEST_NUMBER);
    }

    @Test
    public void setLayoutY() throws Exception {
        textContainer.setLayoutY(TEST_NUMBER);
        assertTrue(textContainer.getLayoutY() == TEST_NUMBER);
    }

    @Test
    public void setText() throws Exception {
        textContainer.setText(TEST_STRING);
        assertTrue(textContainer.getText().equals(TEST_STRING));
    }

    @Test
    public void setIsFocused() {
        boolean isFocused = textContainer.isFocused();
        textContainer.setIsFocused(!isFocused);
        assertFalse(textContainer.isFocused() == isFocused);
    }

    @Test
    public void setIsBold() throws Exception {
        TextContainer.setIsBold(true);
        TextContainer test = new TextContainer(DEFAULT_STRING, DEFAILT_NUMBER, DEFAILT_NUMBER);
        TextContainer.setIsBold(false);
        assertTrue(test.isBold());
    }

    @Test
    public void setIsItalic() throws Exception {
        TextContainer.setIsItalic(true);
        TextContainer test = new TextContainer(DEFAULT_STRING, DEFAILT_NUMBER, DEFAILT_NUMBER);
        TextContainer.setIsItalic(false);
        assertTrue(test.isItalic());
    }

    @Test
    public void duplicate() throws Exception {
        TextContainer test = textContainer.duplicate();
        assertTrue(test.isBold() == textContainer.isBold() && test.isItalic() == textContainer.isItalic() &&
        test.getFontFamilyName().equals(textContainer.getFontFamilyName()) && test.getFontSize() == textContainer.getFontSize() &&
        test.getText().equals(textContainer.getText()));
    }
}