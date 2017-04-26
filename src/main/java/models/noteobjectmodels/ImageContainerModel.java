package models.noteobjectmodels;

import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by svante on 2017-04-26.
 */
public class ImageContainerModel {


    private Image image;
    private File file;

    public ImageContainerModel(Image image){
        this.image = image;
    }

    public ImageContainerModel(File file){
        this.file = file;
    }

    public Image getImage(){
        return this.image;
    }

    public File getFile(){
        return this.file;
    }
}
