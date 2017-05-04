package services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandlerTest {
    @Test
    public void checkFileName1() throws Exception {
        String name = FileHandler.checkFileName("Test.fab");
        assertTrue(name.equals("Test.fab"));
    }

    @Test
    public void checkFileName2() throws Exception {
        String name = FileHandler.checkFileName("test.fab");
        System.out.println(name);
        assertTrue(name.equals("test_1.fab"));
    }

}