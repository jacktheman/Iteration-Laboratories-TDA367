package models.noteobject;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

import java.io.File;

/**
 * Created by svante on 2017-04-26.
 */
public class ImageContainer {

    private Image image;
    private File file;
    private double quota;


    public ImageContainer(Image image){
        this.image = image;
        this.quota = image.getHeight() / image.getWidth();

    }

    public ImageContainer(File file){
        this.file = file;
    }

    public Image getImage(){
        return this.image;
    }

    public File getFile(){
        return this.file;
    }

    public double getQuota() { return this.quota; }

}
