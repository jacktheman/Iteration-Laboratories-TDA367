package com.itlabs.fabnotes.fxml.service;

import com.itlabs.fabnotes.fxml.service.bridge.SavedNoteBridge;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandler {

    private static final String FILE_DIR = System.getProperty("user.home") + File.separator + ".fabNotes";

    public static final String FILE_PATH = FILE_DIR + File.separator;

    public static final String FILE_TYPE = ".fabxml";

    private static final String TAG_LIST = FILE_PATH + "TAG_LIST.txt";

    private FileHandler() {
    }

    private static void createFabNotesFolder(){
        File customDir = new File(FileHandler.FILE_DIR);
        if (!customDir.exists()) {
            if (!customDir.mkdirs()) {
                System.err.println(customDir + " didn't get created");
            }
        }

    }

    private static void createTagListFile () throws IOException {
        createFabNotesFolder();
        File tagList = new File(FileHandler.TAG_LIST);
        if (!tagList.exists()) {
            if (!tagList.createNewFile()) {
                System.err.println(tagList + " didn't get created");
            }
        }
    }

    public static void saveNote(SavedNoteBridge savedNoteBridge) throws TransformerException, ParserConfigurationException {
        createFabNotesFolder();
        savedNoteBridge.save(FILE_PATH, FILE_TYPE);
    }

    public static void  deleteFile(File file){
        if (file.exists()){
            file.delete();
        }
    }

    public static List<String> loadTags() throws IOException {
        createTagListFile();
        File file = new File(TAG_LIST);
            return Files.readAllLines(file.toPath());
    }

    public static void addTags(String... tags) throws IOException {
        createTagListFile();
        File file = new File(TAG_LIST);
        List<String> currentTags = loadTags();
        List<String> totalTags = new ArrayList<>();
        totalTags.addAll(currentTags);
        for (String tag : tags) {
            if (!totalTags.contains(tag))
                totalTags.add(tag);
        }
        if (!currentTags.equals(totalTags)) {
            StringBuffer sb = new StringBuffer();
            for (String tag : totalTags)
                sb.append(tag + "\n");
            Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.WRITE);
        }
    }

    public static void removeTag(String tagToRemove) throws IOException{
        File file = new File(TAG_LIST);
        List<String> currentTags = loadTags();
        currentTags.remove(tagToRemove);
        String [] remainingTags = currentTags.toArray(new String[currentTags.size()]);
        String str = "";
        for (int i = 0; i < remainingTags.length; i++) {
            str = str + remainingTags[i] + "\n";
        }
        file.delete();
        if (file.createNewFile() && str.length() > 0)
            Files.write(file.toPath(), str.substring(0, str.length()-1).getBytes(), StandardOpenOption.WRITE);
    }

    public static List<File> listNotes() {
        createFabNotesFolder();
        File file = new File(FILE_DIR);
        List<File> files = new ArrayList<>();
        files.addAll(Arrays.asList(file.listFiles((file1, s) -> s.contains(FILE_TYPE))));
        return files;
    }

    private static List<File> fileList(String word) {
        List<File> fileList = listNotes();
        for (File file : fileList) {
            if (file.getName().toLowerCase().contains(word.toLowerCase())) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public static List<File> tagList(String word) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        List<File> fileList = listNotes();
        for (int i = 0, fileListSize = fileList.size(); i < fileListSize; i++) {
            File file = fileList.get(i);
            if (file.exists()) {
                for (String tag : SavedNoteBridge.loadSavedNote(file).getTags()) {
                    if (tag.contains(word.toLowerCase())) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    public static List<File> searchList(String word) throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
        List<File> fileList = new ArrayList<>();
        List<File> tagList = tagList(word);
        List<File> sortedList;
        fileList.addAll(fileList(word));
        for (File file : tagList) {
            if (!fileList.contains(file)) {
                fileList.add(file);
            }
        }
        sortedList = sortFiles(fileList);
        return sortedList;
    }

    private static List<File> sortFiles(List<File> fileList) {
        List<String> stringList = new ArrayList<>();
        List<File> sortedList = new ArrayList<>();
        for (File file : fileList) {
            stringList.add(file.getName());
        }
        Collections.sort(stringList);
        for (String string : stringList) {
            for (File file : fileList) {
                if (string.toLowerCase().equals(file.getName().toLowerCase())) {
                    sortedList.add(file);
                }
            }
        }
        return sortedList;
    }

}
