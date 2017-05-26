package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.service.filemanagment.FileHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aron on 2017-05-25.
 */
public class FileHandlerBridge {

    public static List<File> getNotesWithTag(String tag) throws ParserConfigurationException, SAXException, IOException {
        return FileHandler.tagList(tag);
    }

    public static List<File> listNotes() {
        List<File> files = new ArrayList<>();
        files.addAll(Arrays.asList(FileHandler.listNotes()));
        return files;
    }

    public static List<File> searchNotes(String word) throws ParserConfigurationException, SAXException, IOException {
        return FileHandler.searchList(word);
    }

}
