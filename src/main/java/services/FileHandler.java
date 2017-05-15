package services;

import models.note.Note;
import utilities.NoteSave;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandler {

    public static final String FILE_DIR = System.getProperty("user.home") + File.separator + ".fabNotes";

    public static final String FILE_PATH = FILE_DIR + File.separator;

    public static final String FILE_TYPE = ".fab";

    static final String FILE_EXTENSION = "*" + FILE_TYPE;

    static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.png", "*.gif", "*.bmp"};

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

    public static File saveNote(NoteSave noteSave) throws IOException {
        if (noteSave.getModels().size() > 0 && !noteSave.getName().equals("")) {
            String filePath = FILE_PATH + noteSave.getName() + FILE_TYPE;
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(noteSave);
            oos.close();
            return new File(filePath);
        }
        return null;
    }

    public static NoteSave loadNote(File file) throws IOException, ClassNotFoundException {
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object loaded = ois.readObject();
            ois.close();
            if (loaded instanceof NoteSave) {
                NoteSave noteSave = (NoteSave) loaded;
                return noteSave;
            }
        }
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

    public static File[] listNotes() {
        File file = new File(FILE_DIR);
        return file.listFiles((file1, s) -> s.contains(FILE_TYPE));
    }

}
