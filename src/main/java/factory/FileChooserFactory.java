package factory;

import javafx.stage.FileChooser;

/**
 * Created by aron on 2017-04-25.
 */
public class FileChooserFactory {

    private static final String FILE_EXTENSION = "*.fabxml";

    private static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.png", "*.gif", "*.bmp"};

    private FileChooserFactory() {}

    public static FileChooser createFileChooser(String title, String... extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Alla Filtyper", extensions));
        for (String extension : extensions) {
            String description = extension.substring(extension.lastIndexOf(".") + 1).toUpperCase();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(description + " (" + extension + ")", extension));
        }
        return fileChooser;
    }

    public static FileChooser getImageChooser() {
        return createFileChooser("Infoga Bild", IMAGE_EXTENSIONS);
    }

    public static FileChooser getFabNotesChooser() {
        return createFileChooser("Importera Anteckning", FILE_EXTENSION);
    }

}
