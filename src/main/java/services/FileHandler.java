package services;

import javafx.scene.Node;
import models.note.Note;

import java.io.*;
import java.util.List;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandler {

    public static final String FILE_DIR = System.getProperty("user.home") + File.separator + ".fabNotes";

    static final String FILE_PATH = FILE_DIR + File.separator;

    static final String FILE_TYPE = ".fab";

    static final String FILE_EXTENSION = "*" + FILE_TYPE;

    static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.png", "*.gif", "*.bmp"};

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

    public static File saveNote(Note note) throws IOException {
        if (note.getNodes().size() > 0) {
            String filePath = FILE_PATH + note.getName() + FILE_TYPE;
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(note.getNodes());
            oos.close();
            return new File(filePath);
        }
        return null;
    }

    public static Note loadNote(File file) throws IOException, ClassNotFoundException {
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Node> nodes = (List<Node>) ois.readObject();
            Note note = new Note(file.getName().replace(FILE_TYPE, ""));
            for (Node node : nodes)
                note.addNoteObject(node);
            return note;
        }
        return null;
    }
}
