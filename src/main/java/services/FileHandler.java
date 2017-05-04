package services;

import java.io.File;

/**
 * Created by aron on 2017-05-04.
 */
public class FileHandler {

    public static final String FILE_DIR = System.getProperty("user.home") + File.separator + ".fabNotes";

    public static final String FILE_PATH = FILE_DIR + File.separator;

    public static final String FILE_TYPE = ".fab";

    private FileHandler() {}

    public static String checkFileName(String name) {
        File file = new File(FILE_PATH + name);
        System.out.println(FILE_PATH + name);
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
            return checkFileName(fileName + "_" + fileNumber + FILE_TYPE);
        }
        return name;
    }
}
