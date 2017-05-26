package com.itlabs.fabnotes.note.save.xml;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aron on 2017-05-26.
 */
public class XMLAbstractTest {

    private static final String TEST_FILE_TYPE = ".test";

    private static final String TEST_FILE_NAME = "TestString" + TEST_FILE_TYPE;

    @Test
    public void getFileType() throws Exception {
        assertTrue(XMLAbstract.getFileType(TEST_FILE_NAME).equals(TEST_FILE_TYPE));
    }

}