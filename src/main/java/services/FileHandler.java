package services;

import org.xml.sax.SAXException;
import save.NoteSave;

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

    public static final String FILE_DIR = System.getProperty("user.home") + File.separator + ".fabNotes";

    public static final String FILE_PATH = FILE_DIR + File.separator;

    public static final String FILE_TYPE = ".fabxml";

    public static final String TAG_LIST = FILE_PATH + "TAG_LIST.txt";

    private FileHandler() {
    }

    public static String checkFileName(String name) {
        File file = new File(FILE_PATH + name + FILE_TYPE);
        if (file.exists()) {
            int fileNumber = 1;
            String fileName = file.getName().replaceAll(FILE_TYPE, "");
            if (fileName.contains("_")) {
                int index = fileName.lastIndexOf("_") + 1;
                String number = fileName.substring(index);
                if (number.matches("\\d+")) {
                    fileName = fileName.substring(0, index - 1);
                    fileNumber += Integer.parseInt(number);
                }
            }
            return checkFileName(fileName + "_" + fileNumber);
        }
        return name;
    }

    public static void saveNote(NoteSave noteSave) throws TransformerException, ParserConfigurationException {
        if (noteSave.getModels().size() > 0 && !noteSave.getName().equals(""))
            XMLHandler.writeToXML(noteSave);
    }

    public static NoteSave loadNote(File file) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
        if (file.exists())
            return XMLHandler.readXMLToNote(file);
        return null;
    }

    public static List<String> loadTags() throws IOException {
        File file = new File(TAG_LIST);
        if (file.exists()) {
            return Files.readAllLines(file.toPath());
        }
        return null;
    }

    public static void addTags(String... tags) throws IOException {
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


    public static void removeTagFromTagList (String tagToRemove) throws IOException{
        File file = new File(TAG_LIST);
        List<String> currentTags = loadTags();
        currentTags.remove(tagToRemove);
        String [] remainingTags = currentTags.toArray(new String[currentTags.size()]);
        System.out.println(Arrays.toString(remainingTags));
        String str = "";
        for (int i = 0; i < remainingTags.length; i++) {
            str = str + remainingTags[i] + "\n";
        }
        file.delete();
        if (file.createNewFile() && str.length() > 0)
            Files.write(file.toPath(), str.substring(0, str.length()-1).getBytes(), StandardOpenOption.WRITE);
    }

    public static File[] listNotes() {
        File file = new File(FILE_DIR);
        return file.listFiles((file1, s) -> s.contains(FILE_TYPE));
    }

    public static List<File> fileList(String word) {
        File[] fileArray = listNotes();
        List<File> fileList = new ArrayList<>();
        for (File file : fileArray
                ) {
            if (file.getName().toLowerCase().contains(word.toLowerCase())) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public static List<File> tagList(String word) throws IOException, ParserConfigurationException, SAXException {
        File[] fileArray = listNotes();
        List<File> fileList = new ArrayList<>();
        for (File file : fileArray) {
            if (file.exists()) {
                for (String tag : XMLHandler.readXMLToNote(file).getTags()) {
                    if (tag.contains(word.toLowerCase())) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    public static List<File> searchList(String word) throws IOException, ParserConfigurationException, SAXException {
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

    public static List<File> sortFiles(List<File> fileList) {
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
