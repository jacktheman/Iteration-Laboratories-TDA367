package services;

import javafx.stage.FileChooser;

/**
 * Created by aron on 2017-04-25.
 */
public class FileChooserFactory {

    private FileChooserFactory() {}

    public static FileChooser getImageChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Infoga Bild");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Alla bilder", "*.jpg", "*.png", "*.gif", "*.bmp"),
                new FileChooser.ExtensionFilter("JPG (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("GIF (*.gif)", "*.gif"),
                new FileChooser.ExtensionFilter("BMP (*.bmp)", "*.bmp")
        );
        return fileChooser;
    }

    public static FileChooser getFabNotesChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importera Anteckning");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("FAB", "*.fab") //.fab temporary
        );
        return fileChooser;
    }

}
