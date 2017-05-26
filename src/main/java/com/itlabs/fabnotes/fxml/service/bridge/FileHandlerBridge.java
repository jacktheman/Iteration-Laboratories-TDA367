package com.itlabs.fabnotes.fxml.service.bridge;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Created by aron on 2017-05-25.
 */
public class FileHandlerBridge {

    public static void saveNote(SavedNoteBridge savedNoteBridge, String filePath, String fileType) throws TransformerException, ParserConfigurationException {
        savedNoteBridge.save(filePath, fileType);
    }

}
